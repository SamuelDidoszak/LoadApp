package com.didoszak.loadapp.feature_add_find_job.presentation.add_route_screen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.didoszak.loadapp.R
import com.didoszak.loadapp.feature_add_find_job.presentation.job_offers_screen.JobOffersEvent
import com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.LoginRegisterEvent
import com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.HintTextField
import com.didoszak.loadapp.feature_add_find_job.presentation.util.Screen
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddRouteScreen(
    viewModel: AddRouteViewModel = hiltViewModel(),
    navController: NavController
) {
    val stateValue = viewModel
    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    // DatePicker
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val calendar = Calendar.getInstance()
    // Fetching current year, month and day
    mYear = calendar.get(Calendar.YEAR)
    mMonth = calendar.get(Calendar.MONTH)
    mDay = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val startDatePicker = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.startDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    val endDatePicker = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.endDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )



    val textViewWidth = 0.6f

    Scaffold(
        scaffoldState = scaffoldState,
        drawerElevation = 2.dp
    ) {
        if(viewModel.showMap.value) {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            viewModel.onEvent(AddRouteEvent.ClickedPickLocation)
                        },
                        backgroundColor = MaterialTheme.colors.onSurface
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Add a note", tint = MaterialTheme.colors.surface)
                    }
                }
            ) {
                GoogleMap(
                    modifier = Modifier
                        .fillMaxSize(),
                    cameraPositionState = stateValue.cameraPositionState,
                    onMapClick = {
                        viewModel.onEvent(AddRouteEvent.ClickedPosition(it))
                    }
                ) {
                    val position = viewModel.pickedPosition.value
                    if (position.latitude != -1.0 && position.longitude != -1.0)
                    Marker(
                        state = MarkerState(position = LatLng(position.latitude, position.longitude)),
                        alpha = 0.9f
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
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
                            contentDescription = "Profile"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    elevation = 4.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = context.getString(R.string.Start_date),
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onSurface
                        )
                        Text(
                            text = if (viewModel.startDate.value == "") context.getString(R.string.Pick_date) else viewModel.startDate.value,
                            style = MaterialTheme.typography.h5,
                            color = Color(
                                MaterialTheme.colors.onSurface.copy(alpha = 0.44f).toArgb()
                            ),
                            modifier = Modifier
                                .clickable {
                                    startDatePicker.show()
                                }
                        )
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    elevation = 4.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = context.getString(R.string.End_date),
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onSurface
                        )
                        Text(
                            text = if (viewModel.endDate.value == "") context.getString(R.string.Pick_date) else viewModel.endDate.value,
                            style = MaterialTheme.typography.h5,
                            color = Color(
                                MaterialTheme.colors.onSurface.copy(alpha = 0.44f).toArgb()
                            ),
                            modifier = Modifier
                                .clickable {
                                    endDatePicker.show()
                                }
                        )
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                HintTextField(
                    text = viewModel.addPayState.value.text,
                    isHintVisible = viewModel.addPayState.value.isHintVisible,
                    hint = context.getString(R.string.Enter_pay),
                    hasError = viewModel.addPayState.value.hasError,
                    textStyle = MaterialTheme.typography.h5,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(textViewWidth),
                    onValueChange = {
                        viewModel.onEvent(AddRouteEvent.EnteredAddPay(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddRouteEvent.ChangeAddPayFocus(it))
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                HintTextField(
                    text = viewModel.addDescriptionState.value.text,
                    isHintVisible = viewModel.addDescriptionState.value.isHintVisible,
                    hint = context.getString(R.string.Description),
                    hasError = viewModel.addDescriptionState.value.hasError,
                    textStyle = MaterialTheme.typography.h5,
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight(0.7f),
                    onValueChange = {
                        viewModel.onEvent(AddRouteEvent.EnteredDescription(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddRouteEvent.ChangeDescriptionFocus(it))
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HintTextField(
                        text = viewModel.addCityNameState.value.text,
                        isHintVisible = viewModel.addCityNameState.value.isHintVisible,
                        hint = context.getString(R.string.City_name),
                        hasError = viewModel.addCityNameState.value.hasError,
                        textStyle = MaterialTheme.typography.h5,
                        modifier = Modifier.fillMaxWidth(textViewWidth),
                        onValueChange = {
                            viewModel.onEvent(AddRouteEvent.EnteredCityName(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(AddRouteEvent.ChangeCityNameFocus(it))
                        }
                    )
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = context.getString(R.string.Location),
                        isColorButton = true,
                        modifier = Modifier.padding(0.dp),
                        textStyle = MaterialTheme.typography.h6,
                        onClick = {
                            viewModel.onEvent(AddRouteEvent.ClickedPickLocation)
                        }
                    )
                }
                /**
                 * Bottom navigation
                 */
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = context.getString(R.string.Back),
                        isColorButton = true,
                        modifier = Modifier
                            .height(60.dp)
                            .width(128.dp),
                        onClick = {
                            navController.navigateUp()
                        }
                    )
                    com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.components.TextButton(
                        text = context.getString(R.string.Post),
                        isColorButton = true,
                        modifier = Modifier
                            .height(60.dp)
                            .width(128.dp),
                        onClick = {
                            viewModel.onEvent(AddRouteEvent.ClickedPost)
                        }
                    )
                }
            }

        }
    }
}