package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.states

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.didoszak.loadapp.feature_add_find_job.data.model.Language
import com.didoszak.loadapp.feature_add_find_job.data.model.Qualification

data class QualificationsState(
    val qualificationList: List<Qualification> = emptyList(),
    val activeQualifications: List<Int> = emptyList()
)