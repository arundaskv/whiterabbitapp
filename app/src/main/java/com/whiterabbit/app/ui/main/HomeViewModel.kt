package com.whiterabbit.app.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whiterabbit.app.models.Page
import com.whiterabbit.app.models.WidgetItem
import com.whiterabbit.app.utils.AppRepository

class HomeViewModel : ViewModel() {
    var pageContent: MutableLiveData<Page> = MutableLiveData()
    private var appRepository = AppRepository()
    fun getContentListFromJson(context: Context?, pageId: String?) {
        var page: Page? = appRepository.fetchPageDataFromRemoteLocation(context,pageId)
        page = sortByPriority(page)
        pageContent.value = page
    }

    private fun sortByPriority(page: Page?): Page? {
        var sortByPriority : List<WidgetItem>? = page?.widget?.sortedBy { it -> it.priority }
        if (sortByPriority != null) {
            page?.widget = sortByPriority
        }
        return page
    }
}
