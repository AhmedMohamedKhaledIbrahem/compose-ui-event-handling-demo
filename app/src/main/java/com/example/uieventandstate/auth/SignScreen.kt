package com.example.uieventandstate.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.uieventandstate.UiEffect
import com.example.uieventandstate.navigation.NavigateScreen
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignScreen(navController: NavHostController, snackBarHostState: SnackbarHostState) {
    val authViewModel = koinViewModel<AuthViewModel>()
    val state by authViewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        authViewModel.event.collectLatest { event ->
            when (event) {
                is UiEffect.Navigate -> {
                    navController.navigate(NavigateScreen.Home.route) {
                        // popUpTo(NavigateScreen.SignIn.route) { inclusive = true }
                    }
                }

                is UiEffect.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }

                else -> {}
            }
        }
    }
    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.userName,
            onValueChange = { authViewModel.onEvent(AuthUiEvent.Input.UserName(it)) },
            label = { Text("Username") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            onValueChange = { authViewModel.onEvent(AuthUiEvent.Input.Password(it)) },
            label = { Text("Password") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                authViewModel.onEvent(AuthUiEvent.ButtonClick.SignIn)
            },
            enabled = !state.isLoading
        ) {
            AnimatedContent(
                targetState = state.isLoading,
                label = "circularProgressIndicator Sign In"
            ) { isLoading ->
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Sign In")
                }
            }
        }
    }
}