package com.pavelkesler.diploma.domain.network_image

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pavelkesler.diploma.ProcessNumber
import com.pavelkesler.diploma.data.network_image.getImage
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class ImageParseViewModel(application: Application) : AndroidViewModel(application) {

    var images by mutableStateOf(listOf<ImageBitmap>())
        private set

    var loading by mutableStateOf(mutableListOf<Boolean>())
        private set

    init {
        viewModelScope.launch {
            images = listOf()
            loading = mutableListOf(false)
        }
    }

    //  @ObsoleteCoroutinesApi
    //  val fixedContext = newFixedThreadPoolContext(2, "fixed")

    //  @ObsoleteCoroutinesApi
    fun addImage(coroutined: Boolean) {
        loading = mutableListOf(true)
        if (coroutined) {
            for (i in 0..ProcessNumber) {
                println("Adding image by Coroutine $i")
                //  CoroutineScope(fixedContext).launch {
                GlobalScope.launch {
                    val item = getImage()
                    images = images + listOf(item)
                    if (i == ProcessNumber) loading = mutableListOf(false)
                }

            }
        } else {
            for (i in 0..ProcessNumber) {
                println("Adding image by Thread $i")
                thread(start = true) {
                    val item = getImage()
                    images = images + listOf(item)
                    if (i == ProcessNumber) loading = mutableListOf(false)
                }
            }
        }
    }

    fun removeAll() {
        println("Deleting all images")
        images = listOf()
    }
}