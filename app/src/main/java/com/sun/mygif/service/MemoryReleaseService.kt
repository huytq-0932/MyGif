package com.sun.mygif.service

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.system.Os
import android.util.Log
import android.widget.Toast
import com.sun.mygif.cache.CacheHandler
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.lang.Exception

private const val TAG = "MemoryReleaseService"
private const val MILLIS_OF_ONE_WEEK = 604800000L

class MemoryReleaseService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate() {
        super.onCreate()
        releaseCacheMemory()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun releaseCacheMemory() {
        try {
            CacheHandler.getCacheSubDir(this).walkTopDown().forEach {
                if (it.isLongTimeNoAccess()) it.delete()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun File.isLongTimeNoAccess() =
        System.currentTimeMillis() - Os.lstat(absolutePath).st_atime > MILLIS_OF_ONE_WEEK

    companion object {
        @JvmStatic
        fun getIntent(context: Context) = Intent(context, MemoryReleaseService::class.java)

        @JvmStatic
        fun isRunning(context: Context): Boolean {
            val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            return manager.getRunningServices(Integer.MAX_VALUE).any {
                it.service.className == MemoryReleaseService::class.java.name
            }
        }
    }
}
