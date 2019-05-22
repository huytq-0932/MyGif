package com.sun.mygif.data.model

import com.sun.mygif.utils.SEPARATOR_PROPERTY
import java.lang.StringBuilder

data class Topic(val title: String, val gifBackground: Gif) {
    constructor(title: String, width: Int, height: Int, url: String) : this(title, Gif(width, height, url))

    fun encryptToString(): String = StringBuilder().apply {
        append(title)
        append(SEPARATOR_PROPERTY)
        append(gifBackground.id)
        append(SEPARATOR_PROPERTY)
        append(gifBackground.width)
        append(SEPARATOR_PROPERTY)
        append(gifBackground.height)
        append(SEPARATOR_PROPERTY)
        append(gifBackground.url)
    }.toString()
}
