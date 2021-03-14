package com.pavelkesler.diploma.nav

import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.pavelkesler.diploma.nav.Destinations.Home
import com.pavelkesler.diploma.nav.Destinations.ThreadView
import com.pavelkesler.diploma.nav.Destinations.CoroutineView

object Destinations {
    const val Home = "home"
    const val ThreadView = "threads"
    const val CoroutineView = "coroutines"
}

class Action(navController: NavHostController) {
    val home: () -> Unit = { navController.navigate(Home) }
    val threadsView: () -> Unit = { navController.navigate(ThreadView) }
    val coroutinesView: () -> Unit = { navController.navigate(CoroutineView) }
    val navigateBack: () -> Unit = { navController.popBackStack() }
}