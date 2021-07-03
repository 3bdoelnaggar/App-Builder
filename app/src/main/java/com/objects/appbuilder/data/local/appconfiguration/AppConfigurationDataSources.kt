package com.objects.appbuilder.data.local.appconfiguration

import android.content.Context
import com.google.gson.Gson
import com.objects.appbuilder.base.Result
import com.objects.appbuilder.base.safe
import com.objects.appbuilder.data.local.appconfiguration.entities.AppConfiguration
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppConfigurationDataSources @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun getAppConfiguration(): Result<AppConfiguration> {
        return safe {
            val assetManager = context.assets
            val input = assetManager.open("config.json")
            val fileText = input.bufferedReader().use { it.readText() }
            val appConfiguration = Gson().fromJson(fileText, AppConfiguration::class.java)
            appConfiguration
        }
    }
}