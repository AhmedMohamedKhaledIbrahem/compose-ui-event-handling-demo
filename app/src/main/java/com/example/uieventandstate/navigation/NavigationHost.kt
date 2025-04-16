package com.example.uieventandstate.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.uieventandstate.HomeScreen
import com.example.uieventandstate.auth.SignScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationHost(navController: NavHostController) {
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = { TopAppBar(title = { TopScreen(navController = navController) }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.Center
        ) {
            NavigationGraph(
                navController = navController,
                snackBarHostState = snackBarHostState
            )
        }

    }
}

@Composable
private fun NavigationGraph(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = NavigateScreen.SignIn.route
    ) {
        composable(NavigateScreen.SignIn.route) {
            SignScreen(navController = navController,snackBarHostState)
        }
        composable(NavigateScreen.Home.route) {
            HomeScreen()
        }
    }
}


@Composable
fun TopScreen(navController: NavHostController) {
    val currentRoute = currentRoute(navController)
    when (currentRoute) {
        NavigateScreen.SignIn.route -> Text("Sign In")
        NavigateScreen.Home.route -> Text("Home")
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}