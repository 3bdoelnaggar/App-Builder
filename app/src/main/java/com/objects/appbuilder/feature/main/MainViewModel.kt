package com.objects.appbuilder.feature.main

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.objects.appbuilder.R
import com.objects.appbuilder.base.*
import com.objects.appbuilder.data.local.appconfiguration.AppConfigurationDataSources
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
        _effectLiveData.value=Effect.GoTo(R.id.postsFragment, Bundle())
    }
}



sealed class MainState : State() {
    data class Success(val data: UiMainConfiguration) : MainState()
    object Error : MainState()
}
