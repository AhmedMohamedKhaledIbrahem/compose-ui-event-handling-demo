package com.example.uieventandstate.auth

data class AuthScreenState(
    val userName: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)