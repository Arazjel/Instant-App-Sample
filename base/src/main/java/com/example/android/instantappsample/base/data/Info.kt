package com.example.android.instantappsample.base.data

import android.support.annotation.Keep

@Keep
data class Info(
        val count: Long,
        val pages: Long,
        val next: String,
        val prev: String
)