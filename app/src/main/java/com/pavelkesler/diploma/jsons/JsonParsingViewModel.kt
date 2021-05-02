package com.pavelkesler.diploma.jsons

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pavelkesler.diploma.ProcessNumber
import kotlinx.coroutines.*
import kotlin.concurrent.thread

/* class  PlaygroundRun(var min: Int) : Runnable {

    override fun run() {
        // какая-либо работа
        min = min++
        print(min)
    }

    val p = PlaygroundRun(100)
//    Thread(p).start()
} */

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

  //  @ObsoleteCoroutinesApi
  //  val fixedContext = newFixedThreadPoolContext(2, "fixed")

 //   @ObsoleteCoroutinesApi
    fun parseJson(coroutined: Boolean) {
        loading = mutableListOf(true)
        if (coroutined) {
            for (i in 0..ProcessNumber) {
              //  CoroutineScope(fixedContext).launch {
                  GlobalScope.launch {
                    println("Parsing JSON, coroutine $i")
                    val items = retrofitService.getPhotoList().execute().body()
                    viewModelScope.launch {
                        if (items != null) {
                            photos = (photos + items).toMutableList()
                        }
                    }
                }
                if (i== ProcessNumber) loading = mutableListOf(false)
            }
        } else {
            for (i in 0..ProcessNumber) {
                thread(start = true) {
                    println("Parsing JSON, thread $i")
                    val items = retrofitService.getPhotoList().execute().body()
                    if (items != null) {
                        photos = (photos + items).toMutableList()
                    }
                }
                if (i== ProcessNumber) loading = mutableListOf(false)
            }
        }
    }

    fun removeAll() {
        println("Deleting all JSONs")
        photos = mutableListOf()
    }
}