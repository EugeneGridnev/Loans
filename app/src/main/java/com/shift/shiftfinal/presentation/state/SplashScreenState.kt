package com.shift.shiftfinal.presentation.state

sealed interface SplashScreenState {

    data object Loading : SplashScreenState

    data class Error(val message: String) : SplashScreenState
}
