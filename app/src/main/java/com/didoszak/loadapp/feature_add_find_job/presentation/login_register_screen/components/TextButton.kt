package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TextButton(
    text: String = "",
    color: Color = MaterialTheme.colors.surface,
    elevation: Dp = 4.dp,
    modifier: Modifier = Modifier,
    focused: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        elevation = elevation,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = if (focused) 2.dp else 1.dp,
            color = if (focused) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
        ),
        modifier = Modifier
            .height(50.dp)
            .padding(4.dp)
            .shadow(4.dp, RectangleShape)
            .clickable {
                onClick
            }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}