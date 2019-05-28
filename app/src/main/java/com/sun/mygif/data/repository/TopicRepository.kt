package com.sun.mygif.data.repository

import com.sun.mygif.data.model.Topic
import com.sun.mygif.data.model.TopicsResponse
import com.sun.mygif.data.source.OnDataLoadedCallback
import com.sun.mygif.data.source.TopicDataSource
import com.sun.mygif.data.source.local.TopicLocalDataSource
import com.sun.mygif.data.source.remote.TopicRemoteDataSource

class TopicRepository private constructor(
    private val localDataSource: TopicLocalDataSource,
    private val remoteDataSource: TopicRemoteDataSource
) : TopicDataSource.Remote, TopicDataSource.Local {

    override fun getSavedTopicsByTitles(titles: List<String>): List<Topic?> =
        localDataSource.getSavedTopicsByTitles(titles)

    override fun getUpdatedTrendingTopics(callback: OnDataLoadedCallback<TopicsResponse>) =
        remoteDataSource.getUpdatedTrendingTopics(callback)

    override fun saveTopic(topic: Topic) = localDataSource.saveTopic(topic)

    companion object {
        private var sInstance: TopicRepository? = null
        @JvmStatic
        fun getInstance(
            localDataSource: TopicLocalDataSource,
            remoteDataSource: TopicRemoteDataSource
        ): TopicRepository = sInstance ?: TopicRepository(localDataSource, remoteDataSource)
    }
}
