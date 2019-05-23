package com.sun.mygif.data.source

import com.sun.mygif.data.model.Topic
import com.sun.mygif.data.model.TopicsResponse
import com.sun.mygif.data.source.local.base.OnDataLoadedCallback

interface TopicDataSource {
    interface Remote {
        fun getUpdatedTrendingTopics(callback: OnDataLoadedCallback<TopicsResponse>)
    }

    interface Local {
        fun getSavedTrendingTopics(titles: List<String>): List<Topic?>
        fun saveTopic(topic: Topic)
    }
}
