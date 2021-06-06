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
import com.pavelkesler.diploma.encryption.CryptoViewModel
import com.pavelkesler.diploma.ui.theme.AmoledBlack

@Composable
fun CryptoCoroutineView(navController: NavController, cryptoViewModel: CryptoViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Работа с шифрованием", color= Color.White) },
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
                    Button(onClick = { cryptoViewModel.encrypt(true) }) {
                        Text("Корутины")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Button(onClick = { cryptoViewModel.encrypt(false) }) {
                        Text("Потоки")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { cryptoViewModel.removeAll() }) {
                    Text("Очистить экран")
                }
                if (cryptoViewModel.loading[0]) CircularProgressIndicator() else Spacer(modifier = Modifier.height(0.dp))
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(cryptoViewModel.values) {
                        Text(text = "${it.toString(Charsets.UTF_8)} on ${it.toString(Charsets.UTF_8)}", modifier = Modifier
                            .padding(16.dp, 4.dp, 4.dp, 4.dp)
                            .weight(1f, true))
                    }
                }
            }
        },
    )
}