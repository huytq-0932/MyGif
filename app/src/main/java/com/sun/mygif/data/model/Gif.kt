package com.sun.mygif.data.model

import com.sun.mygif.utils.EMPTY_ID

data class Gif(val id: String, val width: Int, val height: Int, val url: String) {
    constructor(width: Int, height: Int, url: String) : this(EMPTY_ID, width, height, url)
}
