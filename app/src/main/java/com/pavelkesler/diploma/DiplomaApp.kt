package com.pavelkesler.diploma

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pavelkesler.diploma.view.CoroutineView
import com.pavelkesler.diploma.view.StartPage
import com.pavelkesler.diploma.view.ThreadView

@Composable
fun DiplomaApp() {
    val navController = rememberNavController()
    MaterialTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                StartPage(navController)
            }
            composable("threadsScreen") {
                ThreadView(navController)
            }
            composable("coroutines") {
                CoroutineView(navController)
            }
        }
    }
}