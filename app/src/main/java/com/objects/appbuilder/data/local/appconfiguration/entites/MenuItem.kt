package com.objects.appbuilder.data.local.appconfiguration.entites


import com.google.gson.annotations.SerializedName

data class MenuItem(
    @SerializedName("component")
    val component: String,
    @SerializedName("parameters")
    val parameters: Parameters,
    @SerializedName("title")
    val title: String
)