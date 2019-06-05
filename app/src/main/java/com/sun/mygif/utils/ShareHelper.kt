package com.sun.mygif.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog

private const val FACEBOOK_PACKAGE = "com.facebook.orca"
private const val INTENT_TYPE_TEXT = "text/plain"

class ShareHelper {
    companion object {
        @JvmStatic
        fun shareLinkToFacebook(activity: Activity, url: String) = ShareDialog(activity).show(
            ShareLinkContent.Builder().apply {
                setContentUrl(Uri.parse(url))
            }.build()
        )

        @JvmStatic
        fun shareLinkToMessenger(activity: Activity, url: String) = activity.startActivity(getMessengerIntent(url))

        @JvmStatic
        private fun getMessengerIntent(url: String) = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(EXTRA_GIF_INFO, url)
            type = INTENT_TYPE_TEXT
            setPackage(FACEBOOK_PACKAGE)
        }
    }
}
