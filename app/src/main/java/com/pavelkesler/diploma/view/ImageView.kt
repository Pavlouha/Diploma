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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pavelkesler.diploma.network_images.ImageParseViewModel
import com.pavelkesler.diploma.ui.theme.AmoledBlack

@Composable
fun ImageCoroutineView(navController: NavController, datas: List<ImageBitmap>, imageViewModel: ImageParseViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Загрузка изображений", color= Color.White) },
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
                Row {
                    Button(onClick = { imageViewModel.addImage(true) }) {
                        Text("Корутины")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Button(onClick = { imageViewModel.addImage(false) }) {
                        Text("Потоки")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { imageViewModel.removeAll() }) {
                    Text("Очистить экран")
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (imageViewModel.loading[0]) CircularProgressIndicator() else Spacer(modifier = Modifier.height(0.dp))
                LazyColumn {
                    items(datas) {
                       androidx.compose.foundation.Image(bitmap = it, contentDescription = "",)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }
            }
        },
    )
}