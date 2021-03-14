package com.pavelkesler.diploma

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun DiplomaApp() {
    MaterialTheme {
        val typography = MaterialTheme.typography
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter  = painterResource(id = R.drawable.logo),
                contentDescription = "")
            Text("Добро пожаловать!", style = typography.h6)
            Spacer(modifier = Modifier.height(1.dp))
            Text("Пожалуйста, выберите раздел", style = typography.h6)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Do something! */ }) {
                Text("Алгоритмы на основе потоков")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Do something! */ }) {
                Text("Алгоритмы на основе корутин")
            }
        }
    }
}