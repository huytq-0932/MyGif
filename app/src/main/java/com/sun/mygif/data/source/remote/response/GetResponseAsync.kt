package com.sun.mygif.data.source.remote.response

import android.os.AsyncTask
import com.sun.mygif.data.model.DataRequest
import com.sun.mygif.data.source.local.base.OnDataLoadedCallback
import org.json.JSONException
import java.io.IOException
import java.lang.Exception

class GetResponseAsync<T>(
    private val responseHandler: DataResponseHandler<T>,
    private val callback: OnDataLoadedCallback<T>
) : AsyncTask<String, Void, Exception?>() {

    private var result: T? = null

    override fun doInBackground(vararg params: String): Exception? =
        try {
            result = responseHandler.getResponse(params[0])
            result ?: throw IOException()
            null
        } catch (exception: IOException) {
            exception
        } catch (exception: JSONException) {
            exception
        }

    override fun onPostExecute(exception: Exception?) {
        exception?.let {
            callback.onFailed(it)
        } ?: result?.let {
            callback.onSuccess(it)
        }
    }
}
