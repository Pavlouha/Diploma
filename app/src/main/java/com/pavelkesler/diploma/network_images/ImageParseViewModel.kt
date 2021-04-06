package com.pavelkesler.diploma.network_images

import android.app.Application
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pavelkesler.diploma.ProcessNumber
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL
import kotlin.concurrent.thread

class ImageParseViewModel (application: Application) : AndroidViewModel(application) {

    private val imgurl: URL = URL("https://www.setaswall.com/wp-content/uploads/2017/03/Artistic-Landscape-4K-Wallpaper-3840x2160.jpg")

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

    fun addImage(coroutined: Boolean) {
        loading = mutableListOf(true)
        if (coroutined) {
            for (i in 0..ProcessNumber) {
                println("Adding image by Coroutine $i")
                GlobalScope.launch {
                    val item = BitmapFactory.decodeStream(imgurl.openConnection().getInputStream()).asImageBitmap()
                    images = images + listOf(item)
                    if (i==ProcessNumber) loading = mutableListOf(false)
                }

            }
        } else {
            for (i in 0..ProcessNumber) {
                println("Adding image by Thread $i")
                thread(start = true) {
                    val item = BitmapFactory.decodeStream(imgurl.openConnection().getInputStream()).asImageBitmap()
                    images = images + listOf(item)
                    if (i==ProcessNumber) loading = mutableListOf(false)
                }
            }
        }
    }

    fun removeAll() {
        println("Deleting all images")
        images = listOf()
    }
}