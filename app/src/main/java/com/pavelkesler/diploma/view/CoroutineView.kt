package com.pavelkesler.diploma.view

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
import androidx.navigation.compose.navigate
import com.pavelkesler.diploma.ui.theme.AmoledBlack

@Composable
fun CoroutineView(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Работа корутин", color= Color.White) },
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
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Нажмите, чтобы открыть:", style = typography.h6)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("coroutinesImages") }) {
                    Text("Http-изображения")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("coroutinesJSON") }) {
                    Text("Загрузку большого JSON")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("coroutinesDatabase") }) {
                    Text("Работу с базами данных")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("coroutinesCrypto") }) {
                    Text("Работу с шифрованием")
                }
            }
        },
    )
}