package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.didoszak.loadapp.feature_add_find_job.data.model.Route

@Composable
fun RouteItem(
    route: Route,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    onClick: () -> Unit,
    onCityClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
//        border = BorderStroke(
//            width = 1.dp,
//            color = MaterialTheme.colors.onSurface
//        ),
        elevation = 2.dp,
        modifier = Modifier
            .shadow(8.dp, RectangleShape)
            .clickable { onClick() }
            .composed { modifier },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = route.organizationName,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                ) {
                    val date = route.start.date.toString() + "." +
                            (if(route.start.month > 9) (route.start.month + 1).toString() else "0" + (route.start.month + 1).toString()) + " - " +
                            route.finish.date.toString() + "." +
                            (if(route.finish.month > 9) (route.finish.month + 1).toString() else "0" + (route.finish.month + 1).toString())
                    Text(
                        text = date,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = route.city?: "",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        modifier = Modifier
                            .clickable { onCityClick() }
                    )
                }
                Text(
                    text = route.gross_pay.toString(),
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                }
                Text(
                    text = route.description,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 15,
                    overflow = TextOverflow.Ellipsis

                )
                Spacer(modifier = Modifier.height(8.dp))
                if(route.truckName != null) {
                    Text(
                        text = "Truck: " + route.truckName,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1
                    )
                }
            }
        }

    }
}