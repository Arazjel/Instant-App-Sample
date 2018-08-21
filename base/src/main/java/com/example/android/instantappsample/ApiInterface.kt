package com.example.android.instantappsample

import com.example.android.instantappsample.data.CharacterPage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("character/")
    fun getCharacterPage(@Query("page")pageNumber: Long): Observable<CharacterPage>

}