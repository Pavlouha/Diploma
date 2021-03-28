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

    var loading by mutableStateOf(mutableListOf<Boolean>())
        private set

    init {
        viewModelScope.launch {
            textRead = listOf("")
            loading = mutableListOf(false)
        }
    }

    fun writeIntoFile(value: String, context: Context) {
        println("Writing into file")
        loading = mutableListOf(true)
        GlobalScope.launch {
            context.openFileOutput("filewrite.txt", Context.MODE_PRIVATE).use {
                it.write(textRead[0].toByteArray() + value.toByteArray())
            }
           val read = context.openFileInput("filewrite.txt").bufferedReader().readText()
            viewModelScope.launch {
                textRead = listOf(read)
                loading = mutableListOf(false)
            }
        }
    }

    fun readFromFile(context: Context) {
        println("Read file")
        GlobalScope.launch {
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
        println("Clearing file")
        textRead = listOf("")
        GlobalScope.launch {
            val file = File(context.filesDir, "filewrite.txt")
            file.delete()
        }
    }
}