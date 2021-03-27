package com.pavelkesler.diploma.files

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pavelkesler.diploma.database.DbLog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.lang.Exception

class FileWorkViewModel(application: Application) : AndroidViewModel(application) {

    var textRead by mutableStateOf(listOf<String>())
        private set

    init {
        viewModelScope.launch {
            textRead = listOf("")
        }
    }

    fun writeIntoFile(value: String, context: Context) {

        GlobalScope.launch {
            println("Writing into file")
            context.openFileOutput("filewrite.txt", Context.MODE_PRIVATE).use {
                it.write(textRead[0].toByteArray() + value.toByteArray())
            }
           val read = context.openFileInput("filewrite.txt").bufferedReader().readText()
            viewModelScope.launch {
                textRead = listOf(read)
            }
        }
    }

    fun readFromFile(context: Context) {
        GlobalScope.launch {
            println("Read file")
            try {
                val read = context.openFileInput("filewrite.txt").bufferedReader().readText()
                viewModelScope.launch {
                    textRead = listOf(read)
                }
            } catch (e: Exception) {
                println(e)
                writeIntoFile("", context)
            }
        }
    }

    fun removeAll(context: Context) {
        textRead = listOf("")
        GlobalScope.launch {
            println("Clearing file")
            val file = File(context.filesDir, "filewrite.txt")
            file.delete()
        }
    }
}