package com.pavelkesler.diploma.jsons

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JsonParsingViewModel (application: Application) : AndroidViewModel(application) {

    private val url: String = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofitService: PhotoInterface
        get() = RetrofitClient.getClient(url).create(PhotoInterface::class.java)

    var photos by mutableStateOf(mutableListOf<PhotoModel>())
        private set

    var loading by mutableStateOf(mutableListOf<Boolean>())
        private set

    init {
        GlobalScope.launch {
            viewModelScope.launch { photos = mutableListOf()
                loading = mutableListOf(false) }
        }
    }

    fun parseJson() {

        println("Parsing big json")

        GlobalScope.launch {
            loading = mutableListOf(true)
            val items = retrofitService.getPhotoList().execute().body()
            viewModelScope.launch {
                if (items != null) {
                    photos = items
                    loading = mutableListOf(false)
                }
            }

        }
    }

    fun removeAll() {
        println("Deleting all jsons")
        photos = mutableListOf()
    }
}