package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didoszak.loadapp.feature_add_find_job.domain.model.InvalidUserException
import com.didoszak.loadapp.feature_add_find_job.domain.model.User
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userUseCases: UserUseCases
) :ViewModel() {
    private val _isLoginVisible = mutableStateOf(true)
    val isLoginVisible: State<Boolean> = _isLoginVisible

    val loginStates = LoginStates()

    val registerStates = RegisterStates()

    fun onEvent(event: LoginRegisterEvent) {
        when(event) {
            is LoginRegisterEvent.ClickedButton -> {
                _isLoginVisible.value = event.isLogin
                Log.d(null, "clicked: " + event.isLogin)
            }

            is LoginRegisterEvent.ChangeEmailFocus -> {
                if(isLoginVisible.value) {
                    loginStates.changeEmail(
                        loginStates.email.value.copy(
                            isHintVisible = !event.focusState.isFocused &&
                                    loginStates.email.value.text.isBlank()
                        )
                    )
                } else {
                    registerStates.changeEmail(
                        registerStates.email.value.copy(
                            isHintVisible = !event.focusState.isFocused &&
                                    registerStates.email.value.text.isBlank()
                        )
                    )
                }
            }
            is LoginRegisterEvent.EnteredEmail -> {
                if(isLoginVisible.value) {
                    Log.d("BRUH", "login")
                    loginStates.changeEmail(
                        loginStates.email.value.copy(
                            text = event.value
                        )
                    )
                } else {
                    Log.d("BRUH", "register")
                    registerStates.changeEmail(
                        registerStates.email.value.copy(
                            text = event.value
                        )
                    )
                }
            }
            is LoginRegisterEvent.ChangePasswordFocus -> {
                if(isLoginVisible.value) {
                    loginStates.changePassword(
                        loginStates.password.value.copy(
                            isHintVisible = !event.focusState.isFocused &&
                                    loginStates.password.value.text.isBlank()
                        )
                    )
                } else {
                    registerStates.changePassword(
                        registerStates.password.value.copy(
                            isHintVisible = !event.focusState.isFocused &&
                                    registerStates.password.value.text.isBlank()
                        )
                    )
                }
            }
            is LoginRegisterEvent.EnteredPassword -> {
                if(isLoginVisible.value) {
                    loginStates.changePassword(
                        loginStates.password.value.copy(
                            text = event.value
                        )
                    )
                } else {
                    registerStates.changePassword(
                        registerStates.password.value.copy(
                            text = event.value
                        )
                    )
                }
            }
            is LoginRegisterEvent.ChangeRepeatPasswordFocus -> {
                registerStates.changeRepeatPassword(
                    registerStates.repeatPassword.value.copy(
                        isHintVisible = !event.focusState.isFocused &&
                                registerStates.repeatPassword.value.text.isBlank()
                    )
                )
            }
            is LoginRegisterEvent.EnteredRepeatPassword -> {
                registerStates.changeRepeatPassword(
                    registerStates.repeatPassword.value.copy(
                    text = event.value
                ))
            }

            is LoginRegisterEvent.SignIn -> {
                viewModelScope.launch {
                    try {
                        userUseCases.insertUser(
                            User(
                                email = loginStates.email.value.text,
                                password = loginStates.password.value.text
                            )
                        )
                    } catch (e: InvalidUserException) {
                        Log.d("ERROR", e.message.toString())

                        if (e.cause?.message.equals("email")) {
                            Log.d("ERROR", "ARHDTYONADHYOINARDANYDOUAN:DUNARWd")
                            loginStates.changeEmail(loginStates.email.value.copy(
                                hasError = true
                            ))
                        } else if(loginStates.email.value.hasError) {
                            loginStates.changeEmail(loginStates.email.value.copy(
                            hasError = false
                            ))
                        }
                        if (e.cause?.message.equals("password")) {
                            loginStates.changePassword(loginStates.password.value.copy(
                                hasError = true
                            ))
                        } else if(loginStates.password.value.hasError) {
                            loginStates.changePassword(loginStates.password.value.copy(
                                hasError = false
                            ))
                        }
                    }
                }
                /*TODO Send data to the server*/
            }
            is LoginRegisterEvent.Next -> {
                /*TODO Parse data*/
            }
        }
    }
}