package com.objects.appbuilder.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class StateViewModel<STATE : State> : ViewModel() {
    protected val _stateLiveData = MutableLiveData<STATE>()
    val stateLiveData: LiveData<STATE> = _stateLiveData

    protected val _effectLiveData = MutableLiveData<Effect>()
    val effectLiveData: LiveData<Effect> = _effectLiveData

    fun onEffectConsumed() {
        _effectLiveData.value = null
    }

}

open class State

sealed class Effect {
    data class ShowToast(val message: String? = null, val messageResId: Int? = null) : Effect()
    data class GoToPosts(val baseUrl: String, val api: String) : Effect()
    data class GoToWebView(val url: String):Effect()
}