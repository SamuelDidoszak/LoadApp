package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers

import com.didoszak.loadapp.feature_add_find_job.data.model.Route
import com.didoszak.loadapp.feature_add_find_job.domain.util.OrderType
import com.didoszak.loadapp.feature_add_find_job.domain.util.RouteOrder
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState

data class JobOffersState(
    val routeList: List<Route> = emptyList(),
    val routeOrder: RouteOrder = RouteOrder.Date(OrderType.Descending),
    val showSorting: Boolean = false,
    val cameraPositionState: CameraPositionState = CameraPositionState()
)
