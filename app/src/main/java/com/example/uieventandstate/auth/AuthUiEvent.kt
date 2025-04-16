package com.example.uieventandstate.auth

sealed class AuthUiEvent {

    sealed class Input {
        data class UserName(val value: String) : AuthUiEvent()
        data class Password(val value: String) : AuthUiEvent()
    }
    sealed class ButtonClick {
        data object SignIn : AuthUiEvent()
    }


}







