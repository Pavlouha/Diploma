package com.pavelkesler.diploma.data.file

import android.content.Context
import java.io.File

object FileRepo {

    fun write(context: Context, value: String) {
        context.openFileOutput("filewrite.txt", Context.MODE_APPEND).use {
            it.write(value.toByteArray())
        }
    }

    fun read(context: Context): String {
        return context.openFileInput("filewrite.txt").bufferedReader().readText()
    }

    fun delete(context: Context) {
        val file = File(context.filesDir, "filewrite.txt")
        file.delete()
    }
}