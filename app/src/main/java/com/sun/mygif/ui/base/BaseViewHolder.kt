package com.sun.mygif.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View

open class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    protected var mainItem: T? = null

    init {
        itemView.setOnClickListener {
            mainItem?.let { onHandleItemClick(it) }
        }
    }

    open fun onBindData(itemData: T) {
        mainItem = itemData
    }

    open fun onHandleItemClick(mainItem: T) {
    }
}
