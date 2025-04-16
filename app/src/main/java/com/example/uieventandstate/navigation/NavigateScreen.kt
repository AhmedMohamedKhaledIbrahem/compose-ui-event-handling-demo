package com.example.uieventandstate.navigation

sealed class NavigateScreen(val route: String) {
    data object SignIn : NavigateScreen("sign_in")
    data object Home : NavigateScreen("home")
}