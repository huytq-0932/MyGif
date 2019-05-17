package com.sun.mygif.data.source

import java.lang.Exception

interface OnDataLoadedListener<T> {
    fun onSuccess(data: T)
    fun onFailed(exception: Exception)
}
