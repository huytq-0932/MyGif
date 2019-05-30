package com.sun.mygif.data.source

import com.sun.mygif.data.model.ParentTopic
import com.sun.mygif.data.model.Topic
import com.sun.mygif.data.model.TopicsResponse

interface TopicDataSource {
    interface Remote {
        fun getUpdatedTrendingTopics(callback: OnDataLoadedCallback<TopicsResponse>)
    }

    interface Local {
        fun getSavedTopicsByTitles(titles: List<String>): List<Topic?>
        fun saveTopic(topic: Topic)
        fun getParentTopicInfos(callback: OnDataLoadedCallback<List<ParentTopic>>)
        fun getSubTopics(callback: OnDataLoadedCallback<List<Topic>>)
    }
}
