package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen

import android.util.Log
import androidx.compose.foundation.layout.*
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
            /**
             * Login / Register textfields
             */
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
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
                            Log.d("BRUH", "text typed in login")
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
                            Log.d("BRUH", "text typed in register")
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
                            viewModel.onEvent(LoginRegisterEvent.ChangeRepeatPasswordFocus(it))
                        }
                    )
                    viewModel.registerStates.repeatPassword.value.copy(hint = hint)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            /**
             * Sign in / Next buttons at the bottom
             */
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
//                    .fillMaxHeight(0.15f)
//                    .height(IntrinsicSize.Min)
//                    .defaultMinSize(minHeight = 120.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
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
                } else {
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
                }
            }

        }
    }
}