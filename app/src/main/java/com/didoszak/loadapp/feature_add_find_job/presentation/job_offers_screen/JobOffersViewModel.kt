package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didoszak.loadapp.feature_add_find_job.data.model.Route
import com.didoszak.loadapp.feature_add_find_job.domain.use_case.ApiUseCases.ApiUseCases
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class JobOffersViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiUseCases: ApiUseCases
) : ViewModel() {

    private val _jobOffersState = mutableStateOf(JobOffersState())
    val jobOffersState: State<JobOffersState> = _jobOffersState

    private val _dummyRoute = Route(id = -1, Date(0), Date(0), "", 0.0, organizationId = -1, organizationName = "")
    private val _clickedRoute = mutableStateOf(_dummyRoute)
    val clickedRoute: State<Route> = _clickedRoute

    val isCompany: Boolean

    init {
        viewModelScope.launch {
            _jobOffersState.value = jobOffersState.value.copy(
                routeList = apiUseCases.getRoutes()
            )
        }
        isCompany = true
    }


    fun onEvent(event: JobOffersEvent) {
        when (event) {
            is JobOffersEvent.RouteClicked -> {
                if(clickedRoute.value != event.route)
                    _clickedRoute.value = event.route
                else
                    _clickedRoute.value = _dummyRoute
            }
            is JobOffersEvent.CityClicked -> {
                if(event.route.latitude == null || event.route.longitude == null)
                    return
                _jobOffersState.value = jobOffersState.value.copy(
                    cameraPositionState = CameraPositionState(CameraPosition(LatLng(event.route.latitude, event.route.longitude), 7.2137f, 0f,0f))
                )
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
                    routeOrder = event.routeOrder,
                    showSheetPartially = false
                )
                apiUseCases.getRoutes(event.routeOrder)
            }

            is JobOffersEvent.MarkerClicked -> {
                if(clickedRoute.value != event.route) {
                    _clickedRoute.value = event.route
                    _jobOffersState.value = jobOffersState.value.copy(
                        currentlyTopItem = event.route,
                        showSheetPartially = true
                    )
                }
                else {
                    _jobOffersState.value = jobOffersState.value.copy(
                        currentlyTopItem = null,
                        showSheetPartially = false
                    )
                    _clickedRoute.value = _dummyRoute
                }
            }
            is JobOffersEvent.HideBottomSheet -> {
                _jobOffersState.value = jobOffersState.value.copy(
                    showSheetPartially = false
                )
            }
        }
    }
}