package com.pavelkesler.diploma

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pavelkesler.diploma.database.DbLogViewModel
import com.pavelkesler.diploma.encryption.CryptoViewModel
import com.pavelkesler.diploma.file.FileWorkViewModel
import com.pavelkesler.diploma.json.JsonParsingViewModel
import com.pavelkesler.diploma.network_image.ImageParseViewModel
import com.pavelkesler.diploma.view.*

@Composable
fun MainAppView(dbViewModel: DbLogViewModel, imageViewModel: ImageParseViewModel,
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
                DbCoroutineView(navController, dbViewModel)
            }
            composable("images") {
                ImageCoroutineView(navController, imageViewModel)
            }
            composable("crypto") {
                CryptoCoroutineView(navController,  cryptoViewModel)
            }
            composable("JSONs") {
                JsonParsingCoroutineView(navController, jsonParsingViewModel)
            }
            composable("files") {
                FileCoroutineView(navController, fileWorkViewModel)
            }
        }
    }
}