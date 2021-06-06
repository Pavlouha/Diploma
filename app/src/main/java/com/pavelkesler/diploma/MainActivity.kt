package com.pavelkesler.diploma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.pavelkesler.diploma.database.DbLogViewModel
import com.pavelkesler.diploma.encryption.CryptoViewModel
import com.pavelkesler.diploma.file.FileWorkViewModel
import com.pavelkesler.diploma.json.JsonParsingViewModel
import com.pavelkesler.diploma.network_image.ImageParseViewModel
import com.pavelkesler.diploma.ui.theme.MainTheme

class MainActivity : ComponentActivity() {

    private val dbLogViewModel by viewModels<DbLogViewModel>()

    private val imageParseViewModel by viewModels<ImageParseViewModel>()

    private val cryptoViewModel by viewModels<CryptoViewModel>()

    private val jsonParseViewModel by viewModels<JsonParsingViewModel>()

    private val fileWorkViewModel by viewModels<FileWorkViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = applicationContext
        fileWorkViewModel.readFromFile(context)
        setContent {
            MainTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainAppView(dbLogViewModel, imageParseViewModel, cryptoViewModel, jsonParseViewModel,
                    fileWorkViewModel)
                }
            }
        }
    }
}