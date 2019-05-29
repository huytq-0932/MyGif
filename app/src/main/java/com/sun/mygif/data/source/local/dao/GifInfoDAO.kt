package com.sun.mygif.data.source.local.dao

import com.sun.mygif.data.model.GifInfo

interface GifInfoDAO {
    fun getAll(): List<GifInfo>
    fun add(gifInfo: GifInfo): Boolean
    fun delete(gifInfo: GifInfo): List<GifInfo>?
    fun added(gifInfo: GifInfo): Boolean
}
