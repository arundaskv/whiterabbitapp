package com.whiterabbit.app.models

import com.google.gson.annotations.SerializedName

data class WidgetItem(
    @SerializedName("items")
    val items: List<ContentItem>,
    @SerializedName("priority")
    val priority: Int,
    @SerializedName("type")
    val type: String
)