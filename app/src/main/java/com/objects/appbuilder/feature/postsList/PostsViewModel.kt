package com.objects.appbuilder.feature.postsList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.objects.appbuilder.base.State
import com.objects.appbuilder.base.StateViewModel
import com.objects.appbuilder.di.NetworkModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class PostsViewModel @Inject constructor() : StateViewModel<PostsState>(), Callback {
    fun fetchData(baseUrl: String, api: String) {
     viewModelScope.launch {
         val retrofit = NetworkModule.provideRetrofit("$baseUrl/")
         val request = Request.Builder().url("$baseUrl/$api").get().build()
         retrofit.callFactory().newCall(request).enqueue(this@PostsViewModel)
     }
    }

    override fun onFailure(call: Call, e: IOException) {
        _stateLiveData.value=PostsState.Error
    }

    override fun onResponse(call: Call, response: Response) {
        _stateLiveData.postValue(PostsState.Success(response.body()?.string()?:""))
    }
}
sealed class PostsState:State(){
    data class Success(val data:String,):PostsState()
    object Error:PostsState()
}