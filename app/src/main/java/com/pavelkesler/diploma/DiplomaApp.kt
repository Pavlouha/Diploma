package com.pavelkesler.diploma

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pavelkesler.diploma.nav.Action
import com.pavelkesler.diploma.view.StartPage

@Composable
fun DiplomaApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    MaterialTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                StartPage()
            }
            composable("addProject") {
                AddProjectScreen()
            }
            composable("addTask") {
                AddTaskScreen()
            }
        }
    }
}