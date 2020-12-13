package com.whiterabbit.app.utils

import android.content.Context
import com.whiterabbit.app.models.Page
import org.json.JSONObject

class AppRepository {
    fun fetchPageDataFromRemoteLocation(context: Context?, pageId:String?): Page? {
        var jsonUtils = JsonUtils()
        var jsonObject = jsonUtils.loadJSONFromAsset(context,pageId)
        var page = jsonUtils.getPageObjectFromJson(jsonObject)
        return page
    }
}