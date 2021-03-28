package com.pavelkesler.diploma

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pavelkesler.diploma.database.DbLogViewModel
import com.pavelkesler.diploma.encrypt.CryptoViewModel
import com.pavelkesler.diploma.files.FileWorkViewModel
import com.pavelkesler.diploma.jsons.JsonParsingViewModel
import com.pavelkesler.diploma.network_images.ImageParseViewModel
import com.pavelkesler.diploma.view.*

@Composable
fun DiplomaApp(dbViewModel: DbLogViewModel, imageViewModel: ImageParseViewModel,
               cryptoViewModel: CryptoViewModel, jsonParsingViewModel: JsonParsingViewModel,
               fileWorkViewModel: FileWorkViewModel) {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                StartPage(navController, dbViewModel)
            }
            composable("mainMenu") {
                MainMenuView(navController)
            }
            composable("database") {
                DbCoroutineView(navController,dbViewModel.logs, dbViewModel)
            }
            composable("images") {
                ImageCoroutineView(navController, imageViewModel.images, imageViewModel)
            }
            composable("crypto") {
                CryptoCoroutineView(navController, cryptoViewModel.values, cryptoViewModel)
            }
            composable("JSONs") {
                JsonParsingCoroutineView(navController, jsonParsingViewModel.photos, jsonParsingViewModel)
            }
            composable("files") {
                FileCoroutineView(navController, fileWorkViewModel.textRead, fileWorkViewModel)
            }
        }
    }
}