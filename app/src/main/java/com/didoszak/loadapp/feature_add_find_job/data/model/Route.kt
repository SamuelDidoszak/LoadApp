package com.didoszak.loadapp.feature_add_find_job.data.model

import java.util.Date


data class Route(
    val id: Int,
    val start: Date,
    val finish: Date,
    val description: String,
    val gross_pay: Double,
    val driver_id: Int? = null,
    val organizationId: Int,
    val organizationName: String,
    val truckName: String? = null,
    val stateId: Int? = null,
    val coordinateX: Double? = null,
    val coordinateY: Double? = null,
    val city: String? = null
)