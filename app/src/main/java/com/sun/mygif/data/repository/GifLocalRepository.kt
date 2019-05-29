package com.sun.mygif.data.repository

import com.sun.mygif.data.model.GifInfo
import com.sun.mygif.data.source.GifDataSource
import com.sun.mygif.data.source.OnDataLoadedCallback
import com.sun.mygif.data.source.local.GifLocalDataSource

class GifLocalRepository(private val dataSource: GifLocalDataSource) : GifDataSource.Local {
    override fun getFavoriteGifInfos(callback: OnDataLoadedCallback<List<GifInfo>>) =
        dataSource.getFavoriteGifInfos(callback)

    override fun addFavorite(gifInfo: GifInfo, callback: OnDataLoadedCallback<Boolean>) =
        dataSource.addFavorite(gifInfo, callback)

    override fun deleteFavorite(gifInfo: GifInfo, callback: OnDataLoadedCallback<List<GifInfo>?>) =
        dataSource.deleteFavorite(gifInfo, callback)

    override fun addedToFavorites(gifInfo: GifInfo, callback: OnDataLoadedCallback<Boolean>) =
        dataSource.addedToFavorites(gifInfo, callback)

    companion object {
        private var sInstance: GifLocalRepository? = null
        @JvmStatic
        fun getInstance(localDataSource: GifLocalDataSource): GifLocalRepository =
            sInstance ?: GifLocalRepository(localDataSource).also { sInstance = it }
    }
}
