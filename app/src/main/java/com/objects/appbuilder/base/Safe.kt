package com.objects.appbuilder.base

import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.io.IOException


sealed class NetworkCallResult<T> {
    open fun get(): T? = null
}

data class NetworkCallSuccess<T>(val data: T) : NetworkCallResult<T>() {
    override fun get(): T = data
}

data class NetworkCallError<T>(val throwable: NetworkError) : NetworkCallResult<T>()


suspend fun <T> safeNetworkCall(call: suspend () -> T): NetworkCallResult<T> {
    return try {
        val result = call.invoke()
        NetworkCallSuccess(result)

    } catch (exception: Exception) {
        exception.printStackTrace()
        NetworkCallError(exception.toFailure())
    }

}


sealed class NetworkError {
    abstract val message: String?

    data class General(override val message: String? = null) : NetworkError()
    data class Unauthorized(override val message: String? = null) : NetworkError()
    data class Validation(override val message: String? = null) : NetworkError()
    data class ServerError(override val message: String? = null) : NetworkError()
    data class NetworkConnection(override val message: String? = null) : NetworkError()
}

fun Throwable.toFailure(): NetworkError {
    return when (this) {
        is HttpException -> {
            when (code()) {
                in 300 until 400 -> NetworkError.Unauthorized("Not Authorized.")
                in 400 until 500 -> NetworkError.Validation("Data not valid.")
                in 500 until 600 -> NetworkError.ServerError("Server is busy. Please try again later.")
                else -> NetworkError.General("Something went wrong. Please try again later.")
            }
        }
        is IOException -> NetworkError.NetworkConnection("Check your internet connection.")
        is JsonParseException -> NetworkError.ServerError("Data does not match")
        else -> NetworkError.General("Something went wrong. Please try again later.")
    }
}

suspend fun <T> safe(call: suspend () -> T): Result<T> {
    return try {
        Success(call())
    } catch (e: Throwable) {
        val newThrowable=Throwable(e.message+" "+call.javaClass.name)
        ErrorResult(newThrowable)
    }

}