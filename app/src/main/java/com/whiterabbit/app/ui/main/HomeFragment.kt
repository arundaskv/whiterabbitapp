package com.whiterabbit.app.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.whiterabbit.app.R
import com.whiterabbit.app.interfaces.IToolBarActions
import com.whiterabbit.app.models.Page
import com.whiterabbit.app.utils.Constants
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    fun setToolbarCallback(toolbarCallback: IToolBarActions){
        toolbarActionCallback = toolbarCallback;
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var toolbarActionCallback : IToolBarActions
    private lateinit var page: Page
    private lateinit var adapter: WidgetListAdapter
    private lateinit var layoutManager : LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        loadContentList(Constants.DEFAULT_PAGE)
        viewModel.pageContent.observe(viewLifecycleOwner, Observer {
            toolbarActionCallback.updateToolbarTitle(it.title)
            page = it
            adapter.updatePage(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

    }

    private fun initRecycler() {
        layoutManager = LinearLayoutManager(context)
        contentList.layoutManager = layoutManager
        adapter = WidgetListAdapter(ArrayList(),context)
        contentList.adapter = adapter
    }

    private fun loadContentList(pageId:String?) {
        viewModel.getContentListFromJson(context,pageId)
    }

}
