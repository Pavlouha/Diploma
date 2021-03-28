package com.pavelkesler.diploma.encrypt

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pavelkesler.diploma.ProcessNumber
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import kotlin.concurrent.thread

class CryptoViewModel (application: Application) : AndroidViewModel(application) {

    var values by mutableStateOf(listOf<ByteArray>())
        private set

    var loading by mutableStateOf(mutableListOf<Boolean>())
        private set

    private val encryptString = "Привет, меня зовут Павел, я обучаюсь в Университете ИТМО".toByteArray()

    private val keygen: KeyGenerator = KeyGenerator.getInstance("AES")

    init {
        keygen.init(256)
        GlobalScope.launch {
            val key: SecretKey = keygen.generateKey()
            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val ciphertext: ByteArray = cipher.doFinal(encryptString)
            val iv: ByteArray = cipher.iv
            viewModelScope.launch {
                values = listOf(ciphertext)
                loading = mutableListOf(false)
            }
        }
    }

    fun addImage(coroutined: Boolean) {
        loading = mutableListOf(true)
        if (coroutined) {
            for (i in 0..ProcessNumber) {
                println("Encrypting value, coroutine $i")
                GlobalScope.launch {
                    val key: SecretKey = keygen.generateKey()
                    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
                    cipher.init(Cipher.ENCRYPT_MODE, key)
                    val ciphertext: ByteArray = cipher.doFinal(encryptString)
                    val iv: ByteArray = cipher.iv

                    val md = MessageDigest.getInstance("SHA-256")
                    val digest: ByteArray = md.digest(ciphertext)
                    viewModelScope.launch {
                        values = values + listOf(iv)
                    }
                }
                if (i==ProcessNumber) loading = mutableListOf(false)
            }
        } else {
            for (i in 0..ProcessNumber) {
                thread(start = true) {
                    println("Encrypting value, thread $i")
                    val key: SecretKey = keygen.generateKey()
                    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
                    cipher.init(Cipher.ENCRYPT_MODE, key)
                    val ciphertext: ByteArray = cipher.doFinal(encryptString)
                    val iv: ByteArray = cipher.iv

                    val md = MessageDigest.getInstance("SHA-256")
                    val digest: ByteArray = md.digest(ciphertext)
                    values = values + listOf(iv)
                    loading = mutableListOf(false)
                }
                if (i==ProcessNumber) loading = mutableListOf(false)
            }
        }
    }

    fun removeAll() {
        println("Deleting all encrypted strings")
        values = listOf()
    }
}