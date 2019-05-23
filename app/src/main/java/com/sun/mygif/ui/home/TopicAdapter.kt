package com.sun.mygif.ui.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.mygif.data.model.Topic
import com.sun.mygif.ui.BaseRecyclerViewAdapter
import com.sun.mygif.widget.BASE_HEIGHT
import kotlinx.android.synthetic.main.item_trending_topic.view.*

private const val TOPIC_WIDTH_DP = 150f

class TopicAdapter(
    private val onItemClick: (topic: Topic) -> Unit
) : BaseRecyclerViewAdapter<Topic, TopicAdapter.TopicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TopicViewHolder = TopicViewHolder(
        LayoutInflater.from(parent.context).inflate(com.sun.mygif.R.layout.item_trending_topic, parent, false),
        onItemClick
    )

    override fun onBindViewHolder(viewHolder: TopicViewHolder, position: Int) {
        getItem(position)?.let { viewHolder.onBindData(it) }
    }

    inner class TopicViewHolder(
        itemView: View,
        private val onItemClick: (topic: Topic) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var topic: Topic? = null

        init {
            itemView.setOnClickListener {
                topic?.let { onItemClick(topic!!) }
            }
        }

        internal fun onBindData(data: Topic) {
            topic = data

            itemView.apply {
                titleItemTopic.text = data.title
                gifItemTopic.run {
                    setRatioSize(
                        ratio = data.gifBackground.width.toFloat() / data.gifBackground.height,
                        size = getPixel(context, TOPIC_WIDTH_DP).toInt(),
                        base = BASE_HEIGHT
                    )
                    gifUrl = data.gifBackground.url
                }
            }
        }

        private fun getPixel(context: Context, dipValue: Float): Float =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context.resources.displayMetrics)
    }
}
