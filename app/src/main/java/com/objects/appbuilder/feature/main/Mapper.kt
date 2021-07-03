package com.objects.appbuilder.feature.main

import android.graphics.Color
import com.objects.appbuilder.data.local.appconfiguration.entities.AppConfiguration
import com.objects.appbuilder.data.local.appconfiguration.entities.MenuItem

fun AppConfiguration.toUiMainConfiguration(): UiMainConfiguration {
    return UiMainConfiguration(
        backgroundColor = Color.parseColor(appColor.pageBg),
        toolbarBackgroundColor = Color.parseColor(appColor.headerBg),
        toolbarTextColor = Color.parseColor(appColor.headerText),
        sideMenuBackgroundColor = Color.parseColor(appColor.menuBg),
        menuItemBackgroundColor = Color.parseColor(appColor.menuItemBGColor),
        menuItemSelectedBackgroundColor = Color.parseColor(appColor.menuItemSelectedBgColor),
        menuItems.map {
            it.toUiMenuItem(
                Color.parseColor(appColor.menuItemBGColor),
                Color.parseColor(appColor.menuItemSelectedBgColor)
            )
        },
        baseUrl = this.mainConfig.baseUrl
    )

}

private fun MenuItem.toUiMenuItem(backgroundColor: Int, selectedColor: Int): UiMenuItem {
    val componentType = when(component){
        "posts"->{
            ComponentType.LIST

        }else->{
            ComponentType.WEB_VIEW
        }
    }
    return UiMenuItem(title, false, backgroundColor, selectedColor,componentType,api = this.parameters.apiName,url = this.parameters.url)
}