package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.didoszak.loadapp.feature_add_find_job.presentation.job_offers.components.RouteItem
import com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.LoginRegisterViewModel

@Composable
fun JobOffersScreen(
    viewModel: JobOffersViewModel = hiltViewModel(),
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(viewModel.jobOffersState.value.routeList) { route ->
                RouteItem(
                    route = route,
                    isExpanded = viewModel.clickedRoute.value == route.id,
                    onClick = {
                        viewModel.onEvent(JobOffersEvent.RouteClicked(route.id))
                    },
                    onCityClick = {
                        viewModel.onEvent(JobOffersEvent.CityClicked(route.id))
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}