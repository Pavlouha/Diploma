package com.pavelkesler.diploma.encrypt

import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

fun encryption(encryptString: ByteArray, keygen: KeyGenerator): ByteArray {
    val key: SecretKey = keygen.generateKey()
    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val ciphertext: ByteArray = cipher.doFinal(encryptString)
    val iv: ByteArray = cipher.iv

    val md = MessageDigest.getInstance("SHA-256")

    return md.digest(ciphertext)
}