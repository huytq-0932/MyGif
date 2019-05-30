package com.sun.mygif.data.source

import com.sun.mygif.data.model.GifInfo
import com.sun.mygif.data.model.GifResponse
import com.sun.mygif.data.model.GifsResponse
import com.sun.mygif.data.model.RandomGifResponse

interface GifDataSource {
    interface Remote {
        fun getTrendingGifs(offset: Int, callback: OnDataLoadedCallback<GifsResponse>)
        fun getSearches(q: String, offset: Int, callback: OnDataLoadedCallback<GifsResponse>)
        fun getGif(gifId: String, callback: OnDataLoadedCallback<GifResponse>)
        fun getRandomGif(q: String, callback: OnDataLoadedCallback<RandomGifResponse>)
        fun getRandomGifs(qList: List<String>, callback: OnDataLoadedCallback<List<RandomGifResponse?>>)
        fun getGifs(gifIds: List<String>, callback: OnDataLoadedCallback<GifsResponse>)
    }

    interface Local {
        fun getFavoriteInfos(callback: OnDataLoadedCallback<List<GifInfo>>)
        fun addFavorite(gifInfo: GifInfo, callback: OnDataLoadedCallback<Boolean>)
        fun deleteFavorite(gifInfo: GifInfo, callback: OnDataLoadedCallback<List<GifInfo>?>)
        fun isFavorite(gifInfo: GifInfo, callback: OnDataLoadedCallback<Boolean>)
    }
}
