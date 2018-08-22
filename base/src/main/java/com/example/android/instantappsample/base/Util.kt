package com.example.android.instantappsample.base

import android.app.Activity
import android.content.Intent
import android.net.Uri
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