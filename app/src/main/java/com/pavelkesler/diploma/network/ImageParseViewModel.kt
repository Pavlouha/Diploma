package com.pavelkesler.diploma.network

import android.app.Application
import android.graphics.drawable.Drawable
import android.media.Image
import android.widget.ImageView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.System.load

class ImageParseViewModel (application: Application) : AndroidViewModel(application) {

    val imgurl = "https://via.placeholder.com/600/771796"

    var images by mutableStateOf(listOf<Image>())
        private set

    init {
        GlobalScope.launch {
            val item =

            viewModelScope.launch { images = images + listOf(item) }
        }
    }
}