package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class LoginStates {
    private val _email = mutableStateOf(HintTextFieldState())
    val email: State<HintTextFieldState> = _email
    fun changeEmail(newValue: HintTextFieldState) {
        _email.value = newValue
    }

    private val _password = mutableStateOf(HintTextFieldState())
    val password: State<HintTextFieldState> = _password
    fun changePassword(newValue: HintTextFieldState) {
        _password.value = newValue
    }
}