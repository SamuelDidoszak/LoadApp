package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didoszak.loadapp.feature_add_find_job.domain.model.InvalidUserException
import com.didoszak.loadapp.feature_add_find_job.domain.model.User
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.ApiUseCases.ApiUseCases
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.UserUseCases
import com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.states.*
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

        if (screenNumber.value == 4 && number == 3) {
            _screenNumber.value = 1
            return
        }

        _screenNumber.value = number
    }

    val loginStates = LoginStates()
    val registerStates = RegisterStates()

    // screen 1 variables
    private val _isDriverClicked = mutableStateOf(false)
    val isDriverClicked: State<Boolean> = _isDriverClicked
    private val _isCompanyClicked = mutableStateOf(false)
    val isCompanyClicked: State<Boolean> = _isCompanyClicked

    // data for driver
    private val _languagesState = mutableStateOf(LanguagesState())
    val languagesState: State<LanguagesState> = _languagesState

    private val _qualificationsState = mutableStateOf(QualificationsState())
    val qualificationsState: State<QualificationsState> = _qualificationsState

    private fun fetchLanguages(){
        viewModelScope.launch {
            _languagesState.value = languagesState.value.copy(
                languageList = apiUseCases.getAllLanguages().data ?: listOf()
            )
        }
    }

    private fun fetchQualifications() {
        viewModelScope.launch {
            _qualificationsState.value = qualificationsState.value.copy(
                qualificationList = apiUseCases.getAllQualifications().data ?: listOf()
            )
        }
    }

    // data for company register
    val companyRegisterStates = CompanyRegisterStates()

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
                    2 -> parseLanguagesScreen()
                    3 -> parseQualificationsScreen()
                }
            }

            is LoginRegisterEvent.Back -> {
                changeScreenNumber(screenNumber.value - 1)
            }

            is LoginRegisterEvent.ClickedDriver -> {
                _isDriverClicked.value = !isDriverClicked.value
                _isCompanyClicked.value = false
                if(languagesState.value.languageList.isEmpty())
                    fetchLanguages()
            }
            is LoginRegisterEvent.ClickedCompany -> {
                _isDriverClicked.value = false
                _isCompanyClicked.value = !isCompanyClicked.value
            }

            is LoginRegisterEvent.ActivateLanguage -> {
                val id = event.id
                _languagesState.value = languagesState.value.copy(
                    activeLanguages = if (languagesState.value.activeLanguages.indexOf(id) == -1)
                        languagesState.value.activeLanguages.plus(id) else
                        languagesState.value.activeLanguages.minus(id)
                )
            }

            is LoginRegisterEvent.ActivateQualification -> {
                val id = event.id
                _qualificationsState.value = qualificationsState.value.copy(
                    activeQualifications = if (qualificationsState.value.activeQualifications.indexOf(id) == -1)
                        qualificationsState.value.activeQualifications.plus(id) else
                        qualificationsState.value.activeQualifications.minus(id)
                )
            }

            is LoginRegisterEvent.ChangeCompanyNameFocus -> {
                companyRegisterStates.changeCompanyName(
                    companyRegisterStates.companyName.value.copy(
                        isHintVisible = !event.focusState.isFocused &&
                                companyRegisterStates.companyName.value.text.isBlank()
                    )
                )
            }
            is LoginRegisterEvent.EnteredCompanyName -> {
                companyRegisterStates.changeCompanyName(
                    companyRegisterStates.companyName.value.copy(
                        text = event.value
                    ))
            }
            is LoginRegisterEvent.ChangeNipFocus -> {
                companyRegisterStates.changeNip(
                    companyRegisterStates.nip.value.copy(
                        isHintVisible = !event.focusState.isFocused &&
                                companyRegisterStates.nip.value.text.isBlank()
                    )
                )
            }
            is LoginRegisterEvent.EnteredNip -> {
                companyRegisterStates.changeNip(
                    companyRegisterStates.nip.value.copy(
                        text = event.value
                    ))
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
            typeId = 1
        else if (isCompanyClicked.value)
            typeId = 2
        else
            return
        viewModelScope.launch {
            userUseCases.insertUser(
                User(
                    email = registerStates.email.value.text,
                    password = registerStates.password.value.text,
                    typeId = typeId
                )
            )
        }
        if (typeId == 1)
            changeScreenNumber(2)
        else
            changeScreenNumber(4)
    }

    private fun parseLanguagesScreen() {
        /*TODO Save ids of chosen languages*/

        changeScreenNumber(3)
        fetchQualifications()
    }

    private fun parseQualificationsScreen() {
        /*TODO Save ids of chosen qualifications*/

    }
}