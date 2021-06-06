package com.pavelkesler.diploma.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pavelkesler.diploma.ui.theme.AmoledBlack

@Composable
fun MainMenuView(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Главное меню", color = Color.White) },
                backgroundColor = AmoledBlack,
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        },
        content = {
            val typography = MaterialTheme.typography
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Нажмите, чтобы открыть:", style = typography.h6)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("images") }) {
                    Text("Изображения из Сети")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("JSONs") }) {
                    Text("Загрузку JSON")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("database") }) {
                    Text("Работу с базами данных")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("crypto") }) {
                    Text("Работу с шифрованием")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("files") }) {
                    Text("Работу с файлами")
                }
            }
        },
    )
}