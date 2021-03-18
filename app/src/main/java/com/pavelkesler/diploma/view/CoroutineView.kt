package com.pavelkesler.diploma.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pavelkesler.diploma.R
import com.pavelkesler.diploma.ui.theme.AmoledBlack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CoroutineView(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Работа корутин") },
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
                Text("Нажмите на кнопку, чтобы запустить", style = typography.h6)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* Do something! */ }) {
                    Text("Http-запросы")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* Do something! */ }) {
                    Text("Загрузка большого JSON")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* Do something! */ }) {
                    Text("Запись в базу данных")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* Do something! */ }) {
                    Text("Чтение из базы данных")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* Do something! */ }) {
                    Text("Работа с шифрованием")
                }
            }
        },
    )
}