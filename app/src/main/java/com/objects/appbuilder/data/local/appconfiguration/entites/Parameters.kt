package com.objects.appbuilder.data.local.appconfiguration.entites


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("apiName")
    val apiName: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("userId")
    val userId: Int
)