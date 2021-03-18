package com.pavelkesler.diploma.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import com.pavelkesler.diploma.R
import com.pavelkesler.diploma.ui.theme.AmoledBlack


@Composable
fun StartPage(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Стартовая страница", color= Color.White) },
                backgroundColor = AmoledBlack,
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
                Button(onClick = { navController.navigate("threadsScreen") {
                } }) {
                    Text("Алгоритмы на основе потоков")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("coroutines") }) {
                    Text("Алгоритмы на основе корутин")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* LATER */ }) {
                    Text("Очистить встроенную базу данных")
                }
            }
        },
    )
}