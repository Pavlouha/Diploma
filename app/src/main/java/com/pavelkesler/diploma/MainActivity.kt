package com.pavelkesler.diploma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.pavelkesler.diploma.database.DbLogViewModel
import com.pavelkesler.diploma.ui.theme.DiplomaTheme


class MainActivity : ComponentActivity() {

    private val dbLogViewModel by viewModels<DbLogViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiplomaTheme {
                Surface(color = MaterialTheme.colors.background) {
                    DiplomaApp(dbLogViewModel)
                }
            }
        }
    }
}