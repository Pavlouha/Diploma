package com.pavelkesler.diploma.domain.encryption

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pavelkesler.diploma.ProcessNumber
import com.pavelkesler.diploma.data.encryption.encryption
import kotlinx.coroutines.*
import javax.crypto.KeyGenerator
import kotlin.concurrent.thread

class CryptoViewModel(application: Application) : AndroidViewModel(application) {

    var values by mutableStateOf(listOf<ByteArray>())
        private set

    var loading by mutableStateOf(mutableListOf<Boolean>())
        private set

    private val encryptString =
        "Привет, меня зовут Павел, я обучаюсь в Университете ИТМО".toByteArray()

    private val keygen: KeyGenerator = KeyGenerator.getInstance("AES")

    //  @ObsoleteCoroutinesApi
    //  val fixedContext = newFixedThreadPoolContext(2, "fixed")

    init {
        keygen.init(256)
        GlobalScope.launch {
            val digest = encryption(encryptString, keygen)
            viewModelScope.launch {
                values = listOf(digest)
                loading = mutableListOf(false)
            }
        }
    }

    @ObsoleteCoroutinesApi
    fun encrypt(coroutined: Boolean) {
        loading = mutableListOf(true)
        if (coroutined) {
            for (i in 0..ProcessNumber) {
                println("Encrypting value, coroutine $i")
                //   CoroutineScope(fixedContext).launch {
                GlobalScope.launch {
                    val digest = encryption(encryptString, keygen)
                    viewModelScope.launch {
                        values = values + listOf(digest)
                    }
                }
            }
        } else {
            for (i in 0..ProcessNumber) {
                thread(start = true) {
                    println("Encrypting value, thread $i")
                    val digest = encryption(encryptString, keygen)
                    values = values + listOf(digest)
                }
            }
        }
        loading = mutableListOf(false)
    }

    fun removeAll() {
        println("Deleting all encrypted strings")
        values = listOf()
    }
}