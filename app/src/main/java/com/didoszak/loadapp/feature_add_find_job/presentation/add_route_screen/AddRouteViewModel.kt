package com.didoszak.loadapp.feature_add_find_job.presentation.add_route_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.states.HintTextFieldState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRouteViewModel @Inject constructor(

) : ViewModel(

) {
    private val _addPayState = mutableStateOf(HintTextFieldState())
    val addPayState: State<HintTextFieldState> = _addPayState

    private val _addDescriptionState = mutableStateOf(HintTextFieldState())
    val addDescriptionState: State<HintTextFieldState> = _addDescriptionState

    private val _addCityNameState = mutableStateOf(HintTextFieldState())
    val addCityNameState: State<HintTextFieldState> = _addCityNameState

    val startDate = mutableStateOf("")
    val endDate = mutableStateOf("")

    private val _showMap = mutableStateOf(false)
    val showMap: State<Boolean> = _showMap

    val cameraPositionState: CameraPositionState = CameraPositionState(CameraPosition(LatLng(50.628642, 19.843361), 7.2137f, 0f,0f))
    private val _pickedPosition = mutableStateOf(LatLng(-1.0, -1.0))
    val pickedPosition: State<LatLng> = _pickedPosition


    fun onEvent(event: AddRouteEvent) {
        when(event) {
            is AddRouteEvent.ChangeAddPayFocus -> {
                _addPayState.value = addPayState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            addPayState.value.text.isBlank()
                )
            }
            is AddRouteEvent.EnteredAddPay -> {
                _addPayState.value = addPayState.value.copy(
                    text = event.value
                )
            }
            is AddRouteEvent.ChangeDescriptionFocus -> {
                _addDescriptionState.value = addDescriptionState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            addDescriptionState.value.text.isBlank()
                )
            }
            is AddRouteEvent.EnteredDescription -> {
                _addDescriptionState.value = addDescriptionState.value.copy(
                    text = event.value
                )
            }
            is AddRouteEvent.ChangeCityNameFocus -> {
                _addCityNameState.value = addCityNameState.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            addCityNameState.value.text.isBlank()
                )
            }
            is AddRouteEvent.EnteredCityName -> {
                _addCityNameState.value = addCityNameState.value.copy(
                    text = event.value
                )
            }
            is AddRouteEvent.ClickedPickLocation -> {
                _showMap.value = !showMap.value
            }
            is AddRouteEvent.ClickedPosition -> {
                _pickedPosition.value = event.position
            }
            is AddRouteEvent.ClickedPost -> {

            }
        }
    }
}