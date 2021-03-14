package com.pavelkesler.diploma.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pavelkesler.diploma.R
import com.pavelkesler.diploma.ui.theme.AmoledBlack


@Composable
fun StartPage() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Стартовая страница") },
                backgroundColor = AmoledBlack,
              /*  navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                } */
            )
        },
        content = {
            val typography = MaterialTheme.typography
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter  = painterResource(id = R.drawable.a), contentDescription = "")
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
        },
    )
}