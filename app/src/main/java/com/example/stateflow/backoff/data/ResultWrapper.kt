package com.example.stateflow.backoff.data

sealed class ResultWrapper {
    data class Success<T>(val value: T) : ResultWrapper()
    data class NetworkError(val errorMessage:String, val retryTime:Int? = 0) : ResultWrapper()
    data class Loading(val isLoading: Boolean = true) : ResultWrapper()
}