package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers_screen

import com.didoszak.loadapp.feature_add_find_job.data.model.Route
import com.didoszak.loadapp.feature_add_find_job.domain.util.RouteOrder

sealed class JobOffersEvent {
    data class RouteClicked(val route: Route): JobOffersEvent()
    data class CityClicked(val route: Route): JobOffersEvent()
    object ShowSorting: JobOffersEvent()
    data class Order(val routeOrder: RouteOrder): JobOffersEvent()
    data class MarkerClicked(val route: Route): JobOffersEvent()
    object HideBottomSheet: JobOffersEvent()
}