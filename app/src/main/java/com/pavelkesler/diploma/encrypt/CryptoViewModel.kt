package com.pavelkesler.diploma.encrypt

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class CryptoViewModel (application: Application) : AndroidViewModel(application) {

    var values by mutableStateOf(listOf<ByteArray>())
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
            viewModelScope.launch { values = listOf(ciphertext) }
        }
    }

    fun addImage() {
        println("Encrypting value")

        GlobalScope.launch {
            val key: SecretKey = keygen.generateKey()
            val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val ciphertext: ByteArray = cipher.doFinal(encryptString)
            val iv: ByteArray = cipher.iv

            val md = MessageDigest.getInstance("SHA-256")
            val digest: ByteArray = md.digest(ciphertext)
            values = values + listOf(iv)
        }
    }

    fun removeAll() {
        println("Deleting all images")
        values = listOf()
    }
}