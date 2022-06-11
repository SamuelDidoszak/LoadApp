package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers

import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.didoszak.loadapp.feature_add_find_job.presentation.job_offers.components.OrderSection
import com.didoszak.loadapp.feature_add_find_job.presentation.job_offers.components.RouteItem
import com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.LoginRegisterViewModel
import com.google.maps.android.compose.GoogleMap

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun JobOffersScreen(
    viewModel: JobOffersViewModel = hiltViewModel(),
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Expanded)
    )
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 180.dp,
            sheetElevation = 0.dp,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 4.dp)
                ) {
                    /**
                     * Top bar
                     */
                    AnimatedVisibility(
                        visible = bottomSheetScaffoldState.bottomSheetState.isExpanded,
                        enter = fadeIn() + slideInVertically() + expandVertically(expandFrom = Alignment.Top),
                        exit = fadeOut() + slideOutVertically() + shrinkVertically()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    viewModel.onEvent(JobOffersEvent.ShowSorting)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Sort,
                                    contentDescription = "Sort notes"
                                )
                            }
                            Text(
                                text = "LoadApp",
                                style = MaterialTheme.typography.h4
                            )
                            IconButton(
                                onClick = {
                                    /*TODO*/
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = "Profile")
                            }
                        }
                    }

                    AnimatedVisibility(
                        visible = viewModel.jobOffersState.value.showSorting,
                        enter = fadeIn() + slideInVertically() + expandVertically(expandFrom = Alignment.Top),
                        exit = fadeOut() + slideOutVertically() + shrinkVertically()
                    ) {
                        OrderSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp, top = 0.dp),
                            routeOrder = viewModel.jobOffersState.value.routeOrder,
                            onOrderChange = {
                                viewModel.onEvent(JobOffersEvent.Order(it))
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

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
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        ) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize(),
                cameraPositionState = viewModel.jobOffersState.value.cameraPositionState
            )
        }
    }
}