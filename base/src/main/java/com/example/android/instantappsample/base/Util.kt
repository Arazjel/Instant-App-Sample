package com.example.android.instantappsample.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import com.google.android.gms.instantapps.InstantApps

object Util {
    fun showInstallPromptIfNeeded(activity: Activity, uriString: String = "") {
        if (InstantApps.getPackageManagerCompat(activity).isInstantApp) {
            InstantApps.showInstallPrompt(
                    activity,
                    Intent(activity, activity::class.java).apply {
                        this.data = Uri.parse(uriString)
                    },
                    203,
                    null
            )
        }
    }
}

fun Context.updateInstantAppCookie(byteArray: ByteArray): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (byteArray.size <= packageManager.instantAppCookieMaxBytes) {
            packageManager.updateInstantAppCookie(byteArray)
        } else {
            return false
        }
    } else {
        val packageManagerCompat = InstantApps.getPackageManagerCompat(this)
        if (byteArray.size <= packageManagerCompat.instantAppCookieMaxSize) {
            packageManagerCompat.setInstantAppCookie(byteArray)
        } else {
            return false
        }
    }

    return true
}

fun Context.getInstantAppCookie(): ByteArray {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        packageManager.instantAppCookie
    } else {
        InstantApps.getPackageManagerCompat(this).instantAppCookie
    }
}