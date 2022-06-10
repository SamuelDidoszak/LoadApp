package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components

import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Start
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun ListItem(
    id: Int,
    text: String = "",
    image: String = "",
    active: Boolean = false,

    elevation: Dp = 4.dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        elevation = elevation,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = if (active) 2.dp else 1.dp,
            color = if (active) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
        ),
        modifier = Modifier
            .padding(4.dp)
            .shadow(8.dp, RectangleShape)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .composed { modifier },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(8.dp)
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = "$text flag",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(8.dp)
                    .heightIn(0.dp, 35.dp),
                imageLoader = ImageLoader.Builder(LocalContext.current)
                    .components {
                        add(SvgDecoder.Factory())
                    }
                    .crossfade(true)
                    .build()
            )
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}