package com.didoszak.loadapp.feature_add_find_job.presentation.util

sealed class Screen (val route: String) {
    object LoginRegisterScreen: Screen("LoginRegisterScreen")
    object JobOffersScreen: Screen("JobOffersScreen")
    object AddRouteScreen: Screen("AddRouteScreen")
}