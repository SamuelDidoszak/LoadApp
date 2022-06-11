package com.didoszak.loadapp.feature_add_find_job.presentation.add_route_screen

import androidx.compose.ui.focus.FocusState
import com.didoszak.loadapp.feature_add_find_job.presentation.login_register_screen.LoginRegisterEvent
import com.google.android.gms.maps.model.LatLng

sealed class AddRouteEvent {
    data class ChangeAddPayFocus(val focusState: FocusState): AddRouteEvent()
    data class EnteredAddPay(val value: String): AddRouteEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): AddRouteEvent()
    data class EnteredDescription(val value: String): AddRouteEvent()
    data class ChangeCityNameFocus(val focusState: FocusState): AddRouteEvent()
    data class EnteredCityName(val value: String): AddRouteEvent()
    object ClickedPickLocation: AddRouteEvent()
    object ClickedPost: AddRouteEvent()
    data class ClickedPosition(val position: LatLng): AddRouteEvent()
}