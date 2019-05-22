package com.sun.mygif.data.source

import com.sun.mygif.data.model.GifResponse
import com.sun.mygif.data.model.GifsResponse
import com.sun.mygif.data.model.RandomGifResponse
import com.sun.mygif.data.source.local.base.OnDataLoadedCallback

interface GifDataSource {
    interface Remote {
        fun getTrendingGifs(offset: Int, callback: OnDataLoadedCallback<GifsResponse>)

        fun getSearches(q: String, offset: Int, callback: OnDataLoadedCallback<GifsResponse>)

        fun getGif(gifId: String, callback: OnDataLoadedCallback<GifResponse>)

        fun getRandomGif(q: String, callback: OnDataLoadedCallback<RandomGifResponse>)

        fun getRandomGifs(qList: List<String>, callback: OnDataLoadedCallback<List<RandomGifResponse?>>)
    }
}
