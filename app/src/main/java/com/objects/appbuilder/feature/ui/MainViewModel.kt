package com.objects.appbuilder.feature.ui

import androidx.lifecycle.viewModelScope
import com.objects.appbuilder.base.ErrorResult
import com.objects.appbuilder.base.State
import com.objects.appbuilder.base.StateViewModel
import com.objects.appbuilder.base.Success
import com.objects.appbuilder.data.local.appconfiguration.AppConfigurationDataSources
import com.objects.appbuilder.data.local.appconfiguration.entites.AppConfiguration
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
                    _stateLiveData.value = MainState.Success(data = appConfiguration)

                }
            }
        }
    }
}

sealed class MainState :State(){
    data class Success(val data: AppConfiguration) : MainState()
    object Error : MainState()
}