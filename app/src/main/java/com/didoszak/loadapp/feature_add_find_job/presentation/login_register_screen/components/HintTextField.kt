package com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HintTextField(
    text: String = "",
    hint: String = "",
    textStyle: TextStyle,
    singleLine: Boolean = true,

    isHintVisible: Boolean = true,
    hasError: Boolean = false,
    elevation: Dp = 2.dp,

    modifier: Modifier = Modifier,
    focused: Boolean = false,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {
    Surface (
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = if (focused) 1.dp else 1.dp,
            color = if (hasError) MaterialTheme.colors.error
                else if (focused) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
        ),
        elevation = elevation,
        modifier = Modifier
            .padding(4.dp)
            .shadow(8.dp, RectangleShape)
            .composed { modifier }
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle.copy(color = MaterialTheme.colors.onSurface),
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .onFocusChanged {
                    onFocusChange(it)
                }
        )
        if(isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = Color(MaterialTheme.colors.onSurface.copy(alpha = 0.3f).toArgb()),
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}