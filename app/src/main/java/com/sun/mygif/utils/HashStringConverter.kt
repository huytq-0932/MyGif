package com.sun.mygif.utils

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

private const val HASH_MD5 = "MD5"
private const val CHARSET_UTF_8 = "UTF-8"
private const val FORMAT_HEX = "%02X"

/** Convert String to MD5 hash. */
@Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
fun String.computeMD5Hash(): ByteArray = MessageDigest.getInstance(HASH_MD5).apply {
    update(toByteArray(charset(CHARSET_UTF_8)), 0, length)
}.digest()

/** Convert array of arbitrary bytes into a String of hexadecimal number-pairs. */
fun ByteArray.toHexString(): String = StringBuilder().also {
    it.append(this.map { byte -> String.format(FORMAT_HEX, byte) })
}.toString()
