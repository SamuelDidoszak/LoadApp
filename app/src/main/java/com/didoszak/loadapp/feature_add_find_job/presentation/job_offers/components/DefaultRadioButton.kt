package com.didoszak.loadapp.feature_add_find_job.presentation.job_offers.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Start
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    isHorizontal: Boolean = true
) {
   Column(
       modifier = modifier,
       horizontalAlignment = Alignment.Start
   ) {
       RadioButton(
           selected = selected,
           onClick = onSelect,
           colors = RadioButtonDefaults.colors(
           selectedColor = MaterialTheme.colors.onSurface,
           unselectedColor = MaterialTheme.colors.onBackground
           ),
           modifier = Modifier
               .padding(0.dp)
               .align(Alignment.Start),
       )
       Text(text = text, style = MaterialTheme.typography.body1, modifier = Modifier.padding(start = 14.dp))
   } 
}