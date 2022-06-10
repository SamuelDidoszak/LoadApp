package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.states

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.states.HintTextFieldState

class RegisterStates {
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

    private val _repeatPassword = mutableStateOf(HintTextFieldState())
    val repeatPassword: State<HintTextFieldState> = _repeatPassword
    fun changeRepeatPassword(newValue: HintTextFieldState) {
        _repeatPassword.value = newValue
    }
}