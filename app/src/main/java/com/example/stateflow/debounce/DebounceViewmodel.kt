package com.example.stateflow.debounce

import androidx.annotation.AnyThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class DebounceViewmodel : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    val networkOperationResult: Flow<String> = _searchQuery.debounce(1000).mapLatest {
        return@mapLatest longNetworkOperation(it)
    }

    @AnyThread
    private suspend fun longNetworkOperation(request: String): String =
        withContext(Dispatchers.Default) {
            delay(3000)
            "Pseudo network delay of 3s delay: $request"
        }
}