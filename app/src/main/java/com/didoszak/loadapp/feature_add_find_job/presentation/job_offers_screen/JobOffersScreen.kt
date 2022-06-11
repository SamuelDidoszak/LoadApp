package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers_screen

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.didoszak.loadapp.feature_add_find_job.presentation.job_offers_screen.components.OrderSection
import com.didoszak.loadapp.feature_add_find_job.presentation.job_offers_screen.components.RouteItem
import com.didoszak.loadapp.feature_add_find_job.presentation.util.Screen
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun JobOffersScreen(
    viewModel: JobOffersViewModel = hiltViewModel(),
    navController: NavController
) {
    val stateValue = viewModel.jobOffersState.value
    val scaffoldState = rememberScaffoldState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = if(!stateValue.showSheetPartially) BottomSheetState(BottomSheetValue.Expanded)
                                else BottomSheetState(BottomSheetValue.Collapsed)
    )

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val listState = rememberLazyListState()

    LaunchedEffect(stateValue.currentlyTopItem) {
        if(stateValue.currentlyTopItem != null)
            listState.scrollToItem(stateValue.routeList.indexOf(stateValue.currentlyTopItem))
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            if (viewModel.isCompany)
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.AddRouteScreen.route)
                    },
                    backgroundColor = MaterialTheme.colors.onSurface
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add a note", tint = MaterialTheme.colors.surface)
                }
        }
    ) {
        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = if(!stateValue.showSheetPartially) 180.dp else 360.dp,
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
                        visible = stateValue.showSorting,
                        enter = fadeIn() + slideInVertically() + expandVertically(expandFrom = Alignment.Top),
                        exit = fadeOut() + slideOutVertically() + shrinkVertically()
                    ) {
                        OrderSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp, top = 0.dp),
                            routeOrder = stateValue.routeOrder,
                            onOrderChange = {
                                viewModel.onEvent(JobOffersEvent.Order(it))
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),

                    ) {
                        items(stateValue.routeList) { route ->
                            RouteItem(
                                route = route,
                                isExpanded = viewModel.clickedRoute.value == route,
                                onClick = {
                                    viewModel.onEvent(JobOffersEvent.RouteClicked(route))
                                },
                                onCityClick = {
                                    viewModel.onEvent(JobOffersEvent.CityClicked(route))
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
                cameraPositionState = stateValue.cameraPositionState,
                onMapClick = {
                    viewModel.onEvent(JobOffersEvent.HideBottomSheet)
                }
            ) {
                stateValue.routeList.forEach { route ->
                    if(route.latitude != null && route.longitude != null)
                    Marker(
                        state = MarkerState(position = LatLng(route.latitude, route.longitude)),
                        title = route.organizationName,
                        alpha = 0.9f,
                        onClick = {
                            viewModel.onEvent(JobOffersEvent.MarkerClicked(route))
                            scope.launch {
                                listState.scrollToItem(stateValue.routeList.indexOf(route))
//                                if(stateValue.currentlyTopItem != null)
//                                    listState.scrollToItem(stateValue.routeList.indexOf(stateValue.currentlyTopItem))
                            }
                            true
                        },
                    )
                }
            }
        }
    }
}