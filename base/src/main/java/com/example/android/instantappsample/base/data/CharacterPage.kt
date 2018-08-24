package com.example.android.instantappsample.base.data

import android.support.annotation.Keep

@Keep
data class CharacterPage(
        val info: Info,
        val results: List<Character>
)