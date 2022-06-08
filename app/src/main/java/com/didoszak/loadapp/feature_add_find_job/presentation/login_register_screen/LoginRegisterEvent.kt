package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen

import androidx.compose.ui.focus.FocusState

sealed class LoginRegisterEvent {
    data class ClickedButton(val isLogin: Boolean): LoginRegisterEvent()
    data class ChangeEmailFocus(val focusState: FocusState): LoginRegisterEvent()
    data class EnteredEmail(val value: String): LoginRegisterEvent()
    data class ChangePasswordFocus(val focusState: FocusState): LoginRegisterEvent()
    data class EnteredPassword(val value: String): LoginRegisterEvent()
    data class ChangeRepeatPasswordFocus(val focusState: FocusState): LoginRegisterEvent()
    data class EnteredRepeatPassword(val value: String): LoginRegisterEvent()
    object SignIn: LoginRegisterEvent()
    object Next: LoginRegisterEvent()
    object Back: LoginRegisterEvent()

    object ClickedDriver: LoginRegisterEvent()
    object ClickedCompany: LoginRegisterEvent()
}