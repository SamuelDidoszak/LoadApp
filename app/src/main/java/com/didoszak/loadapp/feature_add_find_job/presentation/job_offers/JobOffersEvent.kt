package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers

sealed class JobOffersEvent {
    data class RouteClicked(val id: Int): JobOffersEvent()
    data class CityClicked(val id: Int): JobOffersEvent()
}