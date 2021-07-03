package com.objects.appbuilder.feature.ui

import android.graphics.Color
import androidx.lifecycle.viewModelScope
import com.objects.appbuilder.base.ErrorResult
import com.objects.appbuilder.base.State
import com.objects.appbuilder.base.StateViewModel
import com.objects.appbuilder.base.Success
import com.objects.appbuilder.data.local.appconfiguration.AppConfigurationDataSources
import com.objects.appbuilder.data.local.appconfiguration.entites.AppConfiguration
import com.objects.appbuilder.data.local.appconfiguration.entites.MenuItem
import com.objects.appbuilder.feature.main.UiMainConfiguration
import com.objects.appbuilder.feature.main.UiMenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appConfigurationDataSources: AppConfigurationDataSources) :
    StateViewModel<MainState>() {


    fun getAppConfiguration() {
        viewModelScope.launch {
            val result = appConfigurationDataSources.getAppConfiguration()
            when (result) {
                is ErrorResult -> {
                    _stateLiveData.value = MainState.Error
                }
                is Success -> {
                    val appConfiguration = result.data
                    val uiMainConfiguration = appConfiguration.toUiMainConfiguration()
                    _stateLiveData.value = MainState.Success(data = uiMainConfiguration)

                }
            }
        }
    }

    fun selectItem(item: UiMenuItem) {
        val currentState = stateLiveData.value
        if (currentState is MainState.Success) {
            _stateLiveData.value =
                MainState.Success(currentState.data.copy(menuItems = currentState.data.menuItems.map {
                    if (item.title == it.title) {
                        it.copy(isSelected = true)
                    } else {
                        it.copy(isSelected = false)
                    }
                }))
        }
    }
}

private fun AppConfiguration.toUiMainConfiguration(): UiMainConfiguration {

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

sealed class MainState : State() {
    data class Success(val data: UiMainConfiguration) : MainState()
    object Error : MainState()
}