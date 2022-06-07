package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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

    val loginRegisterButtonTexts = listOf(context.getString(R.string.Log_In), context.getString(R.string.Register))

    val loginTexts = listOf(context.getString(R.string.Login),
        context.getString(R.string.Password)
    )

    val registerTexts = listOf(context.getString(R.string.Login),
        context.getString(R.string.Email),
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
//            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                loginRegisterButtonTexts.forEach { text ->
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = text,
                        focused = viewModel.isLoginVisible.value && text == context.getString(R.string.Log_In) ||
                            !viewModel.isLoginVisible.value && text != context.getString(R.string.Log_In)
                    ) {}
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                loginTexts.forEach { hint ->
                    HintTextField(
                        hint = hint,
                        textStyle = MaterialTheme.typography.h5,

                        onValueChange = {
                            // viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                        },
                        onFocusChange = {
                            // viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}