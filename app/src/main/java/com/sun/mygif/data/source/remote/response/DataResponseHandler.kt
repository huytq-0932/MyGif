package com.sun.mygif.data.source.remote.response

import com.sun.mygif.utils.FORMAT_EXCEPTION_IO_HTTP
import com.sun.mygif.utils.METHOD_GET
import com.sun.mygif.data.source.remote.build
import com.sun.mygif.data.source.remote.getJsonString
import org.json.JSONException
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

interface DataResponseHandler<T> {

    @Throws(JSONException::class)
    fun parseToObject(jsonData: String): T

    @Throws(IOException::class, JSONException::class)
    fun getResponse(url: String): T? {
        var responseData: T?
        var connection: HttpURLConnection? = null
        try {
            connection = (URL(url).openConnection() as HttpURLConnection).apply {
                build(METHOD_GET)
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    throw IOException(String.format(FORMAT_EXCEPTION_IO_HTTP, responseCode))
                }

                InputStreamReader(inputStream).run {
                    responseData = parseToObject(getJsonString())
                    close()
                }
            }
        } finally {
            connection?.disconnect()
        }
        return responseData
    }
}
