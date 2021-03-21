package com.pavelkesler.diploma.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pavelkesler.diploma.database.DbLog
import com.pavelkesler.diploma.database.DbLogViewModel
import com.pavelkesler.diploma.ui.theme.AmoledBlack

@Composable
fun DbCoroutineView(navController: NavController, datas: List<DbLog>, viewModel: DbLogViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Android Room", color= Color.White) },
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
                Button(onClick = { viewModel.addDbLog("Entry added") }) {
                    Text("Заполнить данными")
                }
                Text("Данные в базе:", style = typography.h6)
                LazyColumn {
                    items(datas) {
                        Text(text = "${it.dbEvent} on ${it.dateAndTime}", modifier = Modifier
                            .padding(16.dp, 4.dp, 4.dp, 4.dp)
                            .weight(1f, true))
                    }
                }
            }
        },
    )
}