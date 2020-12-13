package com.whiterabbit.app.models

import com.google.gson.annotations.SerializedName

data class ContentItem(
    @SerializedName("deal")
    val deal: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)