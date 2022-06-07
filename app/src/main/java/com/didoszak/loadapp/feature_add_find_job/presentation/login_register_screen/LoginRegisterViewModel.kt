package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) :ViewModel() {
    private val _isLoginVisible = mutableStateOf(true)
    val isLoginVisible: State<Boolean> = _isLoginVisible
}