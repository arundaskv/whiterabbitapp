package com.whiterabbit.app.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.whiterabbit.app.models.Page
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class JsonUtils {

    fun loadJSONFromAsset(context: Context?, pageNumber:String?): JSONObject? {
        var json: String?
        json = try {
            val `is`: InputStream? = context?.assets?.open("page_$pageNumber.json")
            val size: Int = `is`?.available() ?: 0
            val buffer = ByteArray(size)
            `is`?.read(buffer)
            `is`?.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return JSONObject(json)
    }

    fun getPageObjectFromJson(jsonObject: JSONObject?): Page {
        val gson = Gson()
        return gson.fromJson(jsonObject.toString(),object :  TypeToken<Page>() {}.type)
    }
}