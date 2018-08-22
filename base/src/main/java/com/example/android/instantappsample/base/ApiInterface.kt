package com.example.android.instantappsample.base

import com.example.android.instantappsample.base.data.Character
import com.example.android.instantappsample.base.data.CharacterPage
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("character/")
    fun getCharacterPage(@Query("page") pageNumber: Long): Observable<CharacterPage>

    @GET("character/{id}")
    fun fetchCharacterDetails(@Path("id") id: Long): Observable<Character>

}