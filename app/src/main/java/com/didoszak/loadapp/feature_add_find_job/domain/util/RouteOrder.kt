package com.didoszak.loadapp.feature_add_find_job.domain.util

import android.icu.text.CaseMap

sealed class RouteOrder(val orderType: OrderType) {
    class Date(orderType: OrderType): RouteOrder(orderType)
    class Pay(orderType: OrderType): RouteOrder(orderType)
    class Organization(orderType: OrderType): RouteOrder(orderType)
    class City(orderType: OrderType): RouteOrder(orderType)

    fun copy(orderType: OrderType): RouteOrder {
        return when(this) {
            is Date -> Date(orderType)
            is Pay -> Pay(orderType)
            is Organization -> Organization(orderType)
            is City -> City(orderType)
        }
    }
}
