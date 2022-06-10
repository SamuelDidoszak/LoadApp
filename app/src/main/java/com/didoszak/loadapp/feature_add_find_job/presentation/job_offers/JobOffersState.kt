package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers

import com.didoszak.loadapp.feature_add_find_job.data.model.Route

data class JobOffersState(
    val routeList: List<Route> = emptyList()
)
