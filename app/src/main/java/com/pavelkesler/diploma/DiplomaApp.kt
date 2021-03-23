package com.pavelkesler.diploma

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pavelkesler.diploma.database.DbLogViewModel
import com.pavelkesler.diploma.encrypt.CryptoViewModel
import com.pavelkesler.diploma.network.ImageParseViewModel
import com.pavelkesler.diploma.view.*

@Composable
fun DiplomaApp(dbViewModel: DbLogViewModel, imageViewModel: ImageParseViewModel, cryptoViewModel: CryptoViewModel) {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                StartPage(navController, dbViewModel)
            }
            composable("threadsScreen") {
                ThreadView(navController)
            }
            composable("coroutines") {
                CoroutineView(navController)
            }
            composable("coroutinesDatabase") {
                DbCoroutineView(navController,dbViewModel.logs, dbViewModel)
            }
            composable("coroutinesImages") {
                ImageCoroutineView(navController, imageViewModel.images, imageViewModel)
            }
            composable("coroutinesCrypto") {
                CryptoCoroutineView(navController, cryptoViewModel.values, cryptoViewModel)
            }
        }
    }
}