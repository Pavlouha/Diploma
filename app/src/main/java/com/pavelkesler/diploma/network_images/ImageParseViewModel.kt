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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL
import kotlin.concurrent.thread

class ImageParseViewModel (application: Application) : AndroidViewModel(application) {

    private val imgurl: URL = URL("https://png.pngtree.com/element_our/png/20181227/marker-glyph-black-icon-png_293085.jpg")

    var images by mutableStateOf(listOf<ImageBitmap>())
        private set

    var loading by mutableStateOf(mutableListOf<Boolean>())
        private set

    init {
        GlobalScope.launch {
            val item = BitmapFactory.decodeStream(imgurl.openConnection().getInputStream()).asImageBitmap();
            viewModelScope.launch {
                images = listOf(item)
                loading = mutableListOf(false)
            }
        }
    }

    fun addImage(coroutined: Boolean) {
        if (coroutined) {
            println("Adding image by Coroutine")
            loading = mutableListOf(true)
            GlobalScope.launch {
                val item = BitmapFactory.decodeStream(imgurl.openConnection().getInputStream()).asImageBitmap();
                images = images + listOf(item)
                loading = mutableListOf(false)
            }
        } else {
            println("Adding image by Thread")
            loading = mutableListOf(true)
            thread(start = true) {
                    val item = BitmapFactory.decodeStream(imgurl.openConnection().getInputStream()).asImageBitmap();
                    images = images + listOf(item)
                    loading = mutableListOf(false)
            }
        }
    }

    fun removeAll() {
        println("Deleting all images")
        images = listOf()
    }
}