package com.pavelkesler.diploma.view

import androidx.compose.foundation.Image
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
import com.pavelkesler.diploma.jsons.JsonParsingViewModel
import com.pavelkesler.diploma.jsons.PhotoModel
import com.pavelkesler.diploma.ui.theme.AmoledBlack

@Composable
fun JsonParsingCoroutineView(navController: NavController, datas: List<PhotoModel>, jsonParsingViewModel: JsonParsingViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Обработка JSON", color= Color.White) },
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { jsonParsingViewModel.parseJson() }) {
                    Text("Заполнить данными")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { jsonParsingViewModel.removeAll() }) {
                    Text("Очистить экран")
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(datas) {
                        Text(text = it.img_src)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        },
    )
}