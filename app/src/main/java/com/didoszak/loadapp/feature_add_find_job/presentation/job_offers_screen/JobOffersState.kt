package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers_screen

import com.didoszak.loadapp.feature_add_find_job.data.model.Route
import com.didoszak.loadapp.feature_add_find_job.domain.util.OrderType
import com.didoszak.loadapp.feature_add_find_job.domain.util.RouteOrder
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState

data class JobOffersState constructor(
    val routeList: List<Route> = emptyList(),
    val routeOrder: RouteOrder = RouteOrder.Date(OrderType.Descending),
    val showSorting: Boolean = false,
    val cameraPositionState: CameraPositionState = CameraPositionState(CameraPosition(LatLng(50.628642, 19.843361), 7.2137f, 0f,0f)),
    val currentlyTopItem: Route? = null,
    val showSheetPartially: Boolean = false
)
