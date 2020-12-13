package com.whiterabbit.app.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.whiterabbit.app.R
import com.whiterabbit.app.base.BaseActivity
import com.whiterabbit.app.interfaces.IToolBarActions
import com.whiterabbit.app.ui.main.HomeFragment
import com.whiterabbit.app.utils.Constants.Companion.HOME_FRAGMENT
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : BaseActivity() , IToolBarActions, View.OnClickListener {

    private lateinit var titleTextView: TextView
    private lateinit var backIcon: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragment = HomeFragment.newInstance()
            fragment.setToolbarCallback(this)
            addFragment(fragment,HOME_FRAGMENT,false)
        }
        initComponents()
    }

    private fun initComponents() {
        backIcon= toolbar.findViewById(R.id.backIcon) as ImageView
        titleTextView = toolbar.findViewById(R.id.title) as TextView
        backIcon.setOnClickListener(this)
    }

    override fun updateToolbarTitle(title: String?) {
        titleTextView.text = title
    }

    override fun onClick(v: View?) = when(v?.id){
        R.id.backIcon-> {onBackPressed()}
        else -> {}
    }
}
