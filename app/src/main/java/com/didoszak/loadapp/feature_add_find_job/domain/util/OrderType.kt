package com.didoszak.loadapp.feature_add_find_job.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
