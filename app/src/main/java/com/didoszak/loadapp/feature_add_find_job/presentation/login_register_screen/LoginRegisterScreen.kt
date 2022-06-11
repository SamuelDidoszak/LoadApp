package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.didoszak.loadapp.R
import com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.HintTextField
import com.didoszak.loadapp.feature_add_find_job.presentation.util.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginRegisterScreen(
    viewModel: LoginRegisterViewModel = hiltViewModel(),
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val loginRegisterButtonTexts = listOf(context.getString(R.string.Sign_in), context.getString(R.string.Sign_up))

    val loginTexts = listOf(context.getString(R.string.email),
        context.getString(R.string.Password)
    )

    val registerTexts = listOf(context.getString(R.string.email),
        context.getString(R.string.Password),
        context.getString(R.string.Repeat_Password)
    )

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .height(256.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = context.getString(R.string.LoadApp),
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier
                        .align(CenterHorizontally)
                )
            }
            /**
             * Sign in / Sign up buttons
             */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                loginRegisterButtonTexts.forEach { text ->
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = text,
                        focused = viewModel.isLoginVisible.value && text == context.getString(R.string.Sign_in) ||
                            !viewModel.isLoginVisible.value && text != context.getString(R.string.Sign_in),
                        onClick = {
                            viewModel.onEvent(LoginRegisterEvent.ClickedButton(text == context.getString(R.string.Sign_in)))
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /**
                 * Login / Register textfields
                 */
                if (viewModel.screenNumber.value == 0) {
                    if (viewModel.isLoginVisible.value) {
                        // email
                        var hint = loginTexts[0]
                        HintTextField(
                            text = viewModel.loginStates.email.value.text,
                            isHintVisible = viewModel.loginStates.email.value.isHintVisible,
                            hint = hint,
                            hasError = viewModel.loginStates.email.value.hasError,
                            textStyle = MaterialTheme.typography.h5,
                            onValueChange = {
                                viewModel.onEvent(LoginRegisterEvent.EnteredEmail(it))
                            },
                            onFocusChange = {
                                viewModel.onEvent(LoginRegisterEvent.ChangeEmailFocus(it))
                            }
                        )
                        viewModel.loginStates.email.value.copy(hint = hint)
                        // password
                        hint = loginTexts[1]
                        HintTextField(
                            text = viewModel.loginStates.password.value.text,
                            isHintVisible = viewModel.loginStates.password.value.isHintVisible,
                            hint = hint,
                            hasError = viewModel.loginStates.password.value.hasError,
                            textStyle = MaterialTheme.typography.h5,

                            onValueChange = {
                                viewModel.onEvent(LoginRegisterEvent.EnteredPassword(it))
                            },
                            onFocusChange = {
                                viewModel.onEvent(LoginRegisterEvent.ChangePasswordFocus(it))
                            }
                        )
                        viewModel.loginStates.password.value.copy(hint = hint)
                    } else {
                        // email
                        var hint = registerTexts[0]
                        HintTextField(
                            text = viewModel.registerStates.email.value.text,
                            isHintVisible = viewModel.registerStates.email.value.isHintVisible,
                            hint = hint,
                            hasError = viewModel.registerStates.email.value.hasError,
                            textStyle = MaterialTheme.typography.h5,

                            onValueChange = {
                                viewModel.onEvent(LoginRegisterEvent.EnteredEmail(it))
                            },
                            onFocusChange = {
                                viewModel.onEvent(LoginRegisterEvent.ChangeEmailFocus(it))
                            }
                        )
                        viewModel.registerStates.email.value.copy(hint = hint)
                        // password
                        hint = registerTexts[1]
                        HintTextField(
                            text = viewModel.registerStates.password.value.text,
                            isHintVisible = viewModel.registerStates.password.value.isHintVisible,
                            hint = hint,
                            hasError = viewModel.registerStates.password.value.hasError,
                            textStyle = MaterialTheme.typography.h5,

                            onValueChange = {
                                viewModel.onEvent(LoginRegisterEvent.EnteredPassword(it))
                            },
                            onFocusChange = {
                                viewModel.onEvent(LoginRegisterEvent.ChangePasswordFocus(it))
                            }
                        )
                        viewModel.registerStates.password.value.copy(hint = hint)
                        // repeat password
                        hint = registerTexts[2]
                        HintTextField(
                            text = viewModel.registerStates.repeatPassword.value.text,
                            isHintVisible = viewModel.registerStates.repeatPassword.value.isHintVisible,
                            hint = hint,
                            hasError = viewModel.registerStates.repeatPassword.value.hasError,
                            textStyle = MaterialTheme.typography.h5,

                            onValueChange = {
                                viewModel.onEvent(LoginRegisterEvent.EnteredRepeatPassword(it))
                            },
                            onFocusChange = {
                                viewModel.onEvent(
                                    LoginRegisterEvent.ChangeRepeatPasswordFocus(
                                        it
                                    )
                                )
                            }
                        )
                        viewModel.registerStates.repeatPassword.value.copy(hint = hint)
                    }
                }
                /**
                 * Driver / Company screen
                 */
                else if (viewModel.screenNumber.value == 1) {
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = context.getString(R.string.Driver),
                        focused = viewModel.isDriverClicked.value,
                        textStyle = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .height(65.dp)
                            .width(128.dp),
                        onClick = {
                            viewModel.onEvent(LoginRegisterEvent.ClickedDriver)
                        }
                    )
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = context.getString(R.string.Company),
                        focused = viewModel.isCompanyClicked.value,
                        textStyle = MaterialTheme.typography.h5,
                        modifier = Modifier
                            .height(65.dp)
                            .width(128.dp),
                        onClick = {
                            viewModel.onEvent(LoginRegisterEvent.ClickedCompany)
                        }
                    )
                }
                /**
                 * Languages screen
                 */
                else if (viewModel.screenNumber.value == 2) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(viewModel.languagesState.value.languageList) { language ->
                            com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.ListItem(
                                id = language.id,
                                text = language.name,
                                image = language.image,
                                active = viewModel.languagesState.value.activeLanguages.indexOf(language.id) != -1,
                                onClick = {
                                    viewModel.onEvent(LoginRegisterEvent.ActivateLanguage(language.id))
                                }
                            )
                        }
                    }
                }
                /**
                 * Qualifications screen
                 */
                else if (viewModel.screenNumber.value == 3) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(viewModel.qualificationsState.value.qualificationList) { qualification ->
                            com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.ListItem(
                                id = qualification.id,
                                text = qualification.name,
                                image = qualification.image,
                                active = viewModel.qualificationsState.value.activeQualifications.indexOf(qualification.id) != -1,
                                onClick = {
                                    viewModel.onEvent(LoginRegisterEvent.ActivateQualification(qualification.id))
                                }
                            )
                        }
                    }
                }
                /**
                 * Company register screen
                 */
                else if (viewModel.screenNumber.value == 4) {
                    HintTextField(
                        text = viewModel.loginStates.email.value.text,
                        isHintVisible = viewModel.loginStates.email.value.isHintVisible,
                        hint = context.getString(R.string.Company_name),
                        hasError = viewModel.loginStates.email.value.hasError,
                        textStyle = MaterialTheme.typography.h5,
                        onValueChange = {
                            Log.d("BRUH", "text typed in login")
                            viewModel.onEvent(LoginRegisterEvent.EnteredEmail(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(LoginRegisterEvent.ChangeEmailFocus(it))
                        }
                    )
                    HintTextField(
                        text = viewModel.loginStates.email.value.text,
                        isHintVisible = viewModel.loginStates.email.value.isHintVisible,
                        hint = context.getString(R.string.NIP),
                        hasError = viewModel.loginStates.email.value.hasError,
                        textStyle = MaterialTheme.typography.h5,
                        onValueChange = {
                            Log.d("BRUH", "text typed in login")
                            viewModel.onEvent(LoginRegisterEvent.EnteredEmail(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(LoginRegisterEvent.ChangeEmailFocus(it))
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            /**
             * Sign in / Next buttons at the bottom
             */
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                /**
                 * Back button for every view except login / register screen
                 */
                if (viewModel.screenNumber.value != 0) {
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = context.getString(R.string.Back),
                        isColorButton = true,
                        modifier = Modifier
                            .height(80.dp)
                            .width(128.dp),
                        onClick = {
                            viewModel.onEvent(LoginRegisterEvent.Back)
                        }
                    )
                }
                if(viewModel.isLoginVisible.value) {
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = context.getString(R.string.Sign_in),
                        isColorButton = true,
                        modifier = Modifier
                            .height(80.dp)
                            .width(128.dp),
                        onClick = {
                            viewModel.onEvent(LoginRegisterEvent.SignIn)
                        }
                    )
                } else if (viewModel.screenNumber.value != 3){
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = context.getString(R.string.Next),
                        isColorButton = true,
                        modifier = Modifier
                            .height(80.dp)
                            .width(128.dp),
                        onClick = {
                            viewModel.onEvent(LoginRegisterEvent.Next)
                        }
                    )
                } else {
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = context.getString(R.string.Finish),
                        isColorButton = true,
                        modifier = Modifier
                            .height(80.dp)
                            .width(128.dp),
                        onClick = {
                            viewModel.onEvent(LoginRegisterEvent.Finish)
                            navController.navigate(Screen.JobOffersScreen.route)
                        }
                    )
                }
            }
        }
    }
}