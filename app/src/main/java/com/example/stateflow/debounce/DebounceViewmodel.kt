package com.example.stateflow.debounce

import android.util.Log
import androidx.annotation.AnyThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class DebounceViewmodel : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    val networkOperationResult: Flow<String> = _searchQuery.debounce(1000).mapLatest {
        if (it.isEmpty()) {
            return@mapLatest ""
        } else {
            Log.e("Latest Mapped String Query", it)
            return@mapLatest longNetworkOperation(it)
        }
    }

    @AnyThread
    private suspend fun longNetworkOperation(request: String): String =
        withContext(Dispatchers.Default) {
            delay(1000)
            "Pseudo network delay of 3s delay: $request"
        }
}