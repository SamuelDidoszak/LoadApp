package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.dp
import com.didoszak.loadapp.feature_add_find_job.domain.util.OrderType
import com.didoszak.loadapp.feature_add_find_job.domain.util.RouteOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    routeOrder: RouteOrder = RouteOrder.Date(OrderType.Descending),
    onOrderChange: (RouteOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            DefaultRadioButton(text = "Date", selected = routeOrder is RouteOrder.Date, onSelect = { onOrderChange(RouteOrder.Date(routeOrder.orderType)) })
            Spacer(modifier = Modifier.width(16.dp))
            DefaultRadioButton(text = "Pay", selected = routeOrder is RouteOrder.Pay, onSelect = { onOrderChange(RouteOrder.Pay(routeOrder.orderType)) })
            Spacer(modifier = Modifier.width(16.dp))
            DefaultRadioButton(text = "City", selected = routeOrder is RouteOrder.City, onSelect = { onOrderChange(RouteOrder.City(routeOrder.orderType)) })
            Spacer(modifier = Modifier.width(16.dp))
            DefaultRadioButton(text = "Company", selected = routeOrder is RouteOrder.Organization, onSelect = { onOrderChange(RouteOrder.Organization(routeOrder.orderType)) })
            Spacer(modifier = Modifier.width(16.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            DefaultRadioButton(text = "Descending", selected = routeOrder.orderType is OrderType.Descending, onSelect = {
                onOrderChange(routeOrder.copy(OrderType.Descending))
            })
            Spacer(modifier = Modifier.width(24.dp))
            DefaultRadioButton(text = "Ascending", selected = routeOrder.orderType is OrderType.Ascending, onSelect = {
                onOrderChange(routeOrder.copy(OrderType.Ascending))
            })
        }
    }

}