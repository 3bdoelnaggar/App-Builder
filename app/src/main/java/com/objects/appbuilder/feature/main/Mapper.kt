package com.objects.appbuilder.feature.main

import android.graphics.Color
import com.objects.appbuilder.data.local.appconfiguration.entites.AppConfiguration
import com.objects.appbuilder.data.local.appconfiguration.entites.MenuItem

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
        }
    )

}

private fun MenuItem.toUiMenuItem(backgroundColor: Int, selectedColor: Int): UiMenuItem {
    return UiMenuItem(title, false, backgroundColor, selectedColor)
}