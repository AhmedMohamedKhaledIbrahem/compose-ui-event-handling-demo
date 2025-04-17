package com.example.uieventandstate.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uieventandstate.UiEffect
import com.example.uieventandstate.navigation.NavigateScreen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private var _state = MutableStateFlow(AuthScreenState())
    val state: StateFlow<AuthScreenState> = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = AuthScreenState()
    )
    private val _event: Channel<UiEffect> = Channel()
    val event = _event.receiveAsFlow()

    fun onEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.Input.UserName -> {
                _state.update { it.copy(userName = event.value) }
            }

            is AuthUiEvent.Input.Password -> {
                _state.update { it.copy(password = event.value) }
            }

            is AuthUiEvent.ButtonClick.SignIn -> {
                signIn()
            }

        }
    }

    private fun signIn(

    ) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(2000L) //Simulate a network call
            _state.update { it.copy(isLoading = false) }
            if (state.value.userName == "admin" && state.value.password == "123") {
                _event.send(UiEffect.Navigate(NavigateScreen.Home.route))
            } else {
                _event.send(UiEffect.ShowSnackBar(message = "Invalid credentials"))
            }
        }
    }
}