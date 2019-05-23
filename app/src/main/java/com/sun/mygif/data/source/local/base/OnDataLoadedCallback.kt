package com.sun.mygif.data.source.local.base

interface OnDataLoadedCallback<T> {
    fun onSuccess(data: T)
    fun onFailed(exception: Exception)
}
