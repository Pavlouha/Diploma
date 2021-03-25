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

class ImageParseViewModel (application: Application) : AndroidViewModel(application) {

    private val imgurl: URL = URL("https://png.pngtree.com/element_our/png/20181227/marker-glyph-black-icon-png_293085.jpg")

    var images by mutableStateOf(listOf<ImageBitmap>())
        private set

    init {
        GlobalScope.launch {

            val item = BitmapFactory.decodeStream(imgurl.openConnection().getInputStream()).asImageBitmap();
            viewModelScope.launch { images = listOf(item) }
        }
    }

    fun addImage() {

        println("Adding image")

        GlobalScope.launch {
            val item = BitmapFactory.decodeStream(imgurl.openConnection().getInputStream()).asImageBitmap();
            images = images + listOf(item)
        }
    }

    fun removeAll() {
        println("Deleting all images")
        images = listOf()
    }
}