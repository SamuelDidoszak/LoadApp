package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers

import com.didoszak.loadapp.feature_add_find_job.domain.util.RouteOrder

sealed class JobOffersEvent {
    data class RouteClicked(val id: Int): JobOffersEvent()
    data class CityClicked(val id: Int): JobOffersEvent()
    object ShowSorting: JobOffersEvent()
    data class Order(val routeOrder: RouteOrder): JobOffersEvent()
}