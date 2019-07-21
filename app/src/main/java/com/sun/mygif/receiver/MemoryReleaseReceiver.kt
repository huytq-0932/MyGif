package com.sun.mygif.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.system.Os
import android.util.Log
import com.sun.mygif.cache.CacheHandler
import com.sun.mygif.service.MemoryReleaseService
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

private const val TAG = "MemoryReleaseReceiver"

class MemoryReleaseReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onReceive(context: Context, intent: Intent) {
        context.startService(MemoryReleaseService.getIntent(context))
    }

    companion object {
        @JvmStatic
        fun getIntent(context: Context) = Intent(context, MemoryReleaseReceiver::class.java)
    }
}
