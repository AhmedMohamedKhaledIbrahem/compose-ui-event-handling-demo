package com.example.uieventandstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.uieventandstate.navigation.NavigationHost
import com.example.uieventandstate.ui.theme.UiEventAndStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiEventAndStateTheme {
                val navController = rememberNavController()
                NavigationHost(navController = navController)
            }

        }
    }
}

