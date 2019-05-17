package com.sun.mygif.cache

import android.content.Context
import com.sun.mygif.utils.computeMD5Hash
import com.sun.mygif.utils.toHexString
import com.sun.mygif.utils.FORMAT_IOEXEPTION_MESSAGE
import java.io.File
import java.io.IOException

import kotlin.text.StringBuilder

private const val NAME_SUB_DIR = "GifView"

class CacheHandler(private val context: Context) {

    fun getFile(url: String): File? {
        return try {
            getCacheSubDir(context).let {
                if (!it.exists() && !it.mkdirs()) {
                    throw IOException(String.format(FORMAT_IOEXEPTION_MESSAGE, it.absolutePath))
                }
                val pathName = StringBuilder(it.absolutePath).apply {
                    append(File.separator)
                    append(url.computeMD5Hash().toHexString())
                }.toString()

                File(pathName)
            }
        } catch (e: Exception) {
            throw IllegalStateException(e)
        }
    }

    companion object {
        private var cacheSubDir: File? = null
        @JvmStatic
        @Throws(IOException::class)
        private fun getCacheSubDir(context: Context): File {
            val cacheSubDirectoryPathName = StringBuilder(context.cacheDir.toString()).apply {
                append(File.separator)
                append(NAME_SUB_DIR)
            }.toString()
            return cacheSubDir ?: File(cacheSubDirectoryPathName)
        }
    }
}
