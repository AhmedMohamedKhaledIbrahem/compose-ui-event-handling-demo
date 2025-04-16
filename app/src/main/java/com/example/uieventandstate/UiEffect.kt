package com.example.uieventandstate

sealed class UiEffect {
    data object Idle: UiEffect()
    data class Navigate(val route: String) : UiEffect()
    data class ShowSnackBar(val message: String) : UiEffect()

}