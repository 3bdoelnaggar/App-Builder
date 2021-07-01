package com.objects.appbuilder.data.local.appconfiguration.entites


import com.google.gson.annotations.SerializedName

data class AppConfiguration(
    @SerializedName("appColor")
    val appColor: AppColor,
    @SerializedName("mainConfig")
    val mainConfig: MainConfig,
    @SerializedName("menuItems")
    val menuItems: List<MenuItem>
)