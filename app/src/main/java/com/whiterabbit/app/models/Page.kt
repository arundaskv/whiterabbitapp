package com.whiterabbit.app.models
import com.google.gson.annotations.SerializedName


data class Page(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("page_num")
    val pageNum: String,
    @SerializedName("widget")
    val widget: List<WidgetItem>
)







