package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.states

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class CompanyRegisterStates {
    private val _companyName = mutableStateOf(HintTextFieldState())
    val companyName: State<HintTextFieldState> = _companyName
    fun changeCompanyName(newValue: HintTextFieldState) {
        _companyName.value = newValue
    }

    private val _nip = mutableStateOf(HintTextFieldState())
    val nip: State<HintTextFieldState> = _nip
    fun changeNip(newValue: HintTextFieldState) {
        _nip.value = newValue
    }
}
