package com.objects.appbuilder.data.local.appconfiguration.entites


import com.google.gson.annotations.SerializedName

data class MainConfig(
    @SerializedName("baseUrl")
    val baseUrl: String
)