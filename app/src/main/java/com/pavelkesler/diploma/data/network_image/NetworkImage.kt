package com.pavelkesler.diploma.data.network_image

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.net.URL

fun getImage(): ImageBitmap {
    val imgUrl =
        URL("https://www.setaswall.com/wp-content/uploads/2017/03/Artistic-Landscape-4K-Wallpaper-3840x2160.jpg")

    return BitmapFactory.decodeStream(imgUrl.openConnection().getInputStream())
        .asImageBitmap()
}