package com.sun.mygif.data.model

import android.net.Uri
import java.net.URLEncoder

data class DataRequest(
    val scheme: String,
    val authority: String,
    val paths: List<String>,
    val queryParams: Map<String, Any>
) {
    fun toUrl(): String = Uri.Builder().apply {
        scheme(scheme)
        authority(authority)
        paths.forEach {
            appendPath(it)
        }
        queryParams.forEach {
            appendQueryParameter(it.key, URLEncoder.encode(it.value.toString(), "UTF-8"))
        }
    }.toString()
}
