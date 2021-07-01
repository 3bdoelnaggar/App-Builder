package com.objects.appbuilder.data.local.appconfiguration.entites


import com.google.gson.annotations.SerializedName

data class AppColor(
    @SerializedName("headerBg")
    val headerBg: String,
    @SerializedName("headerText")
    val headerText: String,
    @SerializedName("listItemBg")
    val listItemBg: String,
    @SerializedName("listTitle")
    val listTitle: String,
    @SerializedName("menuBg")
    val menuBg: String,
    @SerializedName("menuItemBGColor")
    val menuItemBGColor: String,
    @SerializedName("menuItemSelectedBgColor")
    val menuItemSelectedBgColor: String,
    @SerializedName("pageBg")
    val pageBg: String,
    @SerializedName("textColor")
    val textColor: String
)