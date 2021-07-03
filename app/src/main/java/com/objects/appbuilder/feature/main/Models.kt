package com.objects.appbuilder.feature.main

data class UiMenuItem(
    val title: String,
    val isSelected: Boolean,
    val backgroundColor: Int,
    val selectedColor: Int,
    val componentType:ComponentType,
    val api:String?,
    val url:String?,
)

data class UiMainConfiguration(
    val backgroundColor: Int,
    val toolbarBackgroundColor: Int,
    val toolbarTextColor: Int,
    val sideMenuBackgroundColor: Int,
    val menuItemBackgroundColor: Int,
    val menuItemSelectedBackgroundColor: Int,
    val menuItems: List<UiMenuItem>,
    val baseUrl:String
)

enum class ComponentType{
    LIST,WEB_VIEW
}