package com.objects.appbuilder.data.remote.response


import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)