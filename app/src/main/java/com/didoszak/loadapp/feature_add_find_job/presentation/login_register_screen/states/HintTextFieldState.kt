package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.states

data class HintTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val hasError: Boolean = false
)
