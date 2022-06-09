package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didoszak.loadapp.feature_add_find_job.data.model.Language
import com.didoszak.loadapp.feature_add_find_job.data.model.Qualification
import com.didoszak.loadapp.feature_add_find_job.domain.model.InvalidUserException
import com.didoszak.loadapp.feature_add_find_job.domain.model.User
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.ApiUseCases.ApiUseCases
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userUseCases: UserUseCases,
    private val apiUseCases: ApiUseCases
) :ViewModel() {
    private val _isLoginVisible = mutableStateOf(true)
    val isLoginVisible: State<Boolean> = _isLoginVisible

    private val _screenNumber = mutableStateOf(0)
    val screenNumber: State<Int> = _screenNumber
    private var _previousScreenNumber = 0
    private fun changeScreenNumber(number: Int, previousScreen: Boolean = false) {
        if(previousScreen) {
            _screenNumber.value = _previousScreenNumber
            return
        }

        if (screenNumber.value != 0)
            _previousScreenNumber = screenNumber.value

        _screenNumber.value = number
    }

    val loginStates = LoginStates()
    val registerStates = RegisterStates()

    // screen 1 variables
    private val _isDriverClicked = mutableStateOf(false)
    val isDriverClicked: State<Boolean> = _isDriverClicked
    private val _isCompanyClicked = mutableStateOf(false)
    val isCompanyClicked: State<Boolean> = _isCompanyClicked

    init {
        viewModelScope.launch {
            val languageList: List<Language> = fetchLanguages()
            languageList.forEach { language ->
                Log.d("API", language.name + ", " + language.short_name)
            }
            val qualificationList: List<Qualification> = fetchQualifications()
            languageList.forEach { qualification ->
                Log.d("API", qualification.name + ", " + qualification.short_name)
            }
        }
    }

    private suspend fun fetchLanguages(): List<Language> {
        return apiUseCases.getAllLanguages().data ?: listOf()
    }

    private suspend fun fetchQualifications(): List<Qualification> {
        return apiUseCases.getAllQualifications().data ?: listOf()
    }


    fun onEvent(event: LoginRegisterEvent) {
        when(event) {
            is LoginRegisterEvent.ClickedButton -> {
                _isLoginVisible.value = event.isLogin
                if(event.isLogin)
                    changeScreenNumber(0)
                else
                    changeScreenNumber(0, true)
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
                        if (e.cause?.message.equals("email")) {
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
                when (screenNumber.value) {
                    0 -> parseRegisterScreen()
                    1 -> parseDriverCompanyScreen()
                }
            }

            is LoginRegisterEvent.Back -> {
                changeScreenNumber(screenNumber.value - 1)
            }

            is LoginRegisterEvent.ClickedDriver -> {
                _isDriverClicked.value = !isDriverClicked.value
                _isCompanyClicked.value = false
            }
            is LoginRegisterEvent.ClickedCompany -> {
                _isDriverClicked.value = false
                _isCompanyClicked.value = !isCompanyClicked.value
            }
        }
    }

    private fun parseRegisterScreen() {
        viewModelScope.launch {
            try {
                if(registerStates.password.value.text != registerStates.repeatPassword.value.text)
                    throw InvalidUserException("Passwords must be the same", Throwable("repeatPassword"))
                userUseCases.insertUser(
                    User(
                        email = registerStates.email.value.text,
                        password = registerStates.password.value.text
                    )
                )
                changeScreenNumber(1)
            } catch (e: InvalidUserException) {
                Log.d("ERROR", e.message.toString())
                Log.d("email", registerStates.email.value.text)
                if (e.cause?.message.equals("email")) {
                    registerStates.changeEmail(registerStates.email.value.copy(
                        hasError = true
                    ))
                } else if(registerStates.email.value.hasError) {
                    registerStates.changeEmail(registerStates.email.value.copy(
                        hasError = false
                    ))
                }
                if (e.cause?.message.equals("password")) {
                    registerStates.changePassword(registerStates.password.value.copy(
                        hasError = true
                    ))
                } else if(registerStates.password.value.hasError) {
                    registerStates.changePassword(registerStates.password.value.copy(
                        hasError = false
                    ))
                }
                if (e.cause?.message.equals("repeatPassword")) {
                    registerStates.changeRepeatPassword(registerStates.repeatPassword.value.copy(
                        hasError = true
                    ))
                } else if(registerStates.repeatPassword.value.hasError) {
                    registerStates.changeRepeatPassword(registerStates.repeatPassword.value.copy(
                        hasError = false
                    ))
                }
            }
        }
    }

    private fun parseDriverCompanyScreen() {
        val typeId: Int
        if (isDriverClicked.value)
            typeId = 0
        else if (isCompanyClicked.value)
            typeId = 1
        else
            return
        viewModelScope.launch {
            userUseCases.insertUser(
                User(
                    email = loginStates.email.value.text,
                    password = loginStates.password.value.text,
                    typeId = typeId
                )
            )
        }
        changeScreenNumber(2)
    }
}