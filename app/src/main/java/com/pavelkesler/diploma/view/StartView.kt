package com.pavelkesler.diploma.view

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.pavelkesler.diploma.R
import com.pavelkesler.diploma.database.DbLogViewModel
import com.pavelkesler.diploma.ui.theme.AmoledBlack

@Composable
fun StartPage(navController: NavController, viewModel: DbLogViewModel) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Добро пожаловать", color= Color.White) },
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
                Text("Производитель: " +
                        " ${Build.MANUFACTURER}",
                    style = typography.h6)
                Text("Модель: " +
                        " ${Build.MODEL}",
                    style = typography.h6)
                Text("Процессор: " +
                        " ${Build.HARDWARE}",
                    style = typography.h6)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { navController.navigate("mainMenu") }) {
                    Text("Открыть главное меню")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.removeAll()
                    Toast.makeText(
                        context,
                        "База данных очищена",
                        Toast.LENGTH_SHORT
                    ).show()}) {
                    Text("Очистить встроенную базу данных")
                }
            }
        },
    )
}