package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.ApiUseCases.ApiUseCases
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.user_use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobOffersViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiUseCases: ApiUseCases
) : ViewModel() {

    private val _jobOffersState = mutableStateOf(JobOffersState())
    val jobOffersState: State<JobOffersState> = _jobOffersState

    private val _clickedRoute = mutableStateOf(99999)
    val clickedRoute: State<Int> = _clickedRoute

    init {
        viewModelScope.launch {
            _jobOffersState.value = jobOffersState.value.copy(
                routeList = apiUseCases.getRoutes()
            )
        }
    }


    fun onEvent(event: JobOffersEvent) {
        when (event) {
            is JobOffersEvent.RouteClicked -> {
                if(clickedRoute.value != event.id)
                    _clickedRoute.value = event.id
                else
                    _clickedRoute.value = 999999
            }
            is JobOffersEvent.CityClicked -> {
                Log.d("ROUTE", "CITY Clicked: " + event.id)
            }
            is JobOffersEvent.ShowSorting -> {
                _jobOffersState.value = jobOffersState.value.copy(
                    showSorting = !jobOffersState.value.showSorting
                )
            }
            is JobOffersEvent.Order -> {
                if(jobOffersState.value.routeOrder::class == event.routeOrder::class &&
                    jobOffersState.value.routeOrder.orderType == event.routeOrder.orderType)
                    return
                _jobOffersState.value = jobOffersState.value.copy(
                    routeOrder = event.routeOrder
                )
                apiUseCases.getRoutes(event.routeOrder)
            }
        }
    }
}