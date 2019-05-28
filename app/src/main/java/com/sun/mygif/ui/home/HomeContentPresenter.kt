package com.sun.mygif.ui.home

import com.sun.mygif.data.model.*
import com.sun.mygif.data.repository.GifRemoteRepository
import com.sun.mygif.data.repository.TopicRepository
import com.sun.mygif.data.source.OnDataLoadedCallback

private const val TAG = "HomeContentPresenter"

class HomeContentPresenter(
    private val homeView: HomeContentContract.View,
    private val topicRepository: TopicRepository,
    private val gifRepository: GifRemoteRepository
) : HomeContentContract.Presenter {

    init {
        homeView.setPresenter(this)
    }

    override fun start() {
        getTrendingTopics()
        getTrendingGifs(0)
    }

    override fun getTrendingTopics() {
        topicRepository.getUpdatedTrendingTopics(object :
            OnDataLoadedCallback<TopicsResponse> {
            override fun onSuccess(data: TopicsResponse) {
                val topics = topicRepository.getSavedTopicsByTitles(data.results).also {
                    homeView.appendTrendingTopics(it.filterNotNull())
                }

                val unsavedTopicTitles = ArrayList<String>().apply {
                    topics.forEachIndexed { index, topic -> topic ?: add(data.results[index]) }
                }
                if (unsavedTopicTitles.isNotEmpty()) getGifBackgroundTopics(unsavedTopicTitles)
            }

            override fun onFailed(exception: Exception) {
                homeView.toast(exception.message.toString())
            }
        })
    }

    private fun getGifBackgroundTopics(titles: List<String>) {
        gifRepository.getRandomGifs(titles, object :
            OnDataLoadedCallback<List<RandomGifResponse?>> {
            override fun onSuccess(data: List<RandomGifResponse?>) {
                val topics = data.mapIndexed { index, response ->
                    response?.let {
                        Topic(title = titles[index], gifBackground = Gif(it.results[0])).also { topic ->
                            topicRepository.saveTopic(topic)
                        }
                    }
                }
                homeView.appendTrendingTopics(topics.filterNotNull())
            }

            override fun onFailed(exception: Exception) {
                homeView.toast(exception.message.toString())
            }
        })
    }

    override fun getTrendingGifs(offset: Int) {
        homeView.showLoading()

        gifRepository.getTrendingGifs(offset, object : OnDataLoadedCallback<GifsResponse> {
            override fun onSuccess(data: GifsResponse) {
                val gifs = data.data.map { Gif(it) }
                with(homeView) {
                    appendTrendingGifs(gifs)
                    hideLoading()
                }
            }

            override fun onFailed(exception: Exception) {
                with(homeView) {
                    toast(exception.message.toString())
                    hideLoading()
                }
            }
        })
    }
}
