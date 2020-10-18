package com.example.stateflow.validation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class ValidationViewmodel : ViewModel() {

    private val _firstName = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _userID = MutableStateFlow("")

    fun setFirstName(name: String) {
        _firstName.value = name
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setUserId(id: String) {
        _userID.value = id
    }

    val isSubmitEnabled: Flow<Boolean> = _firstName.combine(_password) { a, b ->
        val regexString = "[a-zA-Z]+"
        val isNameCorrect = a.matches(regexString.toRegex())
        val isPasswordCorrect = b.length > 8
        return@combine isNameCorrect and isPasswordCorrect
    }.combine(_userID) { result, userId ->
        val isUserIdCorrect = userId.contains("_")
        return@combine result and isUserIdCorrect
    }

}