package com.sun.mygif.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.mygif.R
import com.sun.mygif.data.model.Gif
import com.sun.mygif.ui.BaseRecyclerViewAdapter
import com.sun.mygif.utils.screenWidth
import com.sun.mygif.widget.BASE_WIDTH
import kotlinx.android.synthetic.main.item_gif_vertical.view.*

class GifsAdapter(
    private val onItemClick: (gif: Gif?) -> Unit
) : BaseRecyclerViewAdapter<Gif, GifsAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): SearchViewHolder = SearchViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_gif_vertical, parent, false),
        onItemClick
    )

    override fun onBindViewHolder(viewHolder: SearchViewHolder, position: Int) {
        getItem(position)?.let { viewHolder.onBindData(it) }
    }

    inner class SearchViewHolder(
        itemView: View,
        private val onItemClick: (gif: Gif?) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var clickedItem: Gif? = null

        init {
            itemView.setOnClickListener {
                clickedItem?.let { onItemClick(it) }
            }
        }

        internal fun onBindData(gif: Gif) {
            clickedItem = gif
            itemView.gifItemVertical.apply {
                setRatioSize(
                    ratio = gif.width.toFloat() / gif.height,
                    size = screenWidth ushr 1,
                    base = BASE_WIDTH
                )
                gifUrl = gif.url
            }
        }
    }
}
