package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TextButton(
    text: String = "",
    isColorButton: Boolean = false,
    elevation: Dp = 4.dp,
    textStyle: TextStyle = MaterialTheme.typography.h6,
    modifier: Modifier = Modifier,
    focused: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        elevation = elevation,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = if (focused) 2.dp else 1.dp,
            color = if (isColorButton) Color.Black else if (focused) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
        ),
        modifier = Modifier
            .padding(4.dp)
            .shadow(8.dp, RectangleShape)
            .clickable {
                onClick()
            }
            .composed { modifier },
        color = if(isColorButton) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                style = textStyle,
                modifier = Modifier
                    .padding(8.dp)
            )
        }

    }
}