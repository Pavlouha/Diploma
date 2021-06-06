package com.pavelkesler.diploma.domain.file

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pavelkesler.diploma.ProcessNumber
import com.pavelkesler.diploma.data.file.FileRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class FileWorkViewModel(application: Application) : AndroidViewModel(application) {

    var textRead by mutableStateOf(listOf<String>())
        private set

    var loading by mutableStateOf(mutableListOf<Boolean>())
        private set

    //  @ObsoleteCoroutinesApi
    //  val fixedContext = newFixedThreadPoolContext(2, "fixed")

    init {
        viewModelScope.launch {
            textRead = listOf("")
            loading = mutableListOf(false)
        }
    }

    //  @ObsoleteCoroutinesApi
    fun writeIntoFile(value: String, context: Context, coroutined: Boolean) {
        loading = mutableListOf(true)
        if (coroutined) {
            for (i in 0..ProcessNumber) {
                //  CoroutineScope(fixedContext).launch {
                GlobalScope.launch {
                    println("Writing into file (Coroutine) $i")
                    FileRepo.write(context, value)
                    if (i == ProcessNumber) {
                        val read = FileRepo.read(context)
                        viewModelScope.launch {
                            textRead = listOf(read)
                            loading = mutableListOf(false)
                        }
                    }
                }

            }
        } else {
            for (i in 0..ProcessNumber) {
                thread(start = true) {
                    println("Writing into file (Thread) $i")
                    FileRepo.write(context, value)
                }
                if (i == ProcessNumber) {
                    val read = FileRepo.read(context)
                    textRead = listOf(read)
                    loading = mutableListOf(false)
                }
            }
        }
    }


    fun readFromFile(context: Context) {
        println("Read file")
        GlobalScope.launch {
            try {
                val read = FileRepo.read(context)
                viewModelScope.launch {
                    textRead = listOf(read)
                }
            } catch (e: Exception) {
                println(e)
                writeIntoFile("", context, true)
            }
        }
    }

    fun removeAll(context: Context) {
        println("Clearing file")
        textRead = listOf("")
        GlobalScope.launch {
            FileRepo.delete(context)
        }
    }
}