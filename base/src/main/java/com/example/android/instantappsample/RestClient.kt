package com.example.android.instantappsample

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object RestClient {
    val apiInterface: ApiInterface by lazy {
        val loginInterceptor = HttpLoggingInterceptor {
            Timber.w(it)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
                .addInterceptor(loginInterceptor)
                .build()

        Retrofit.Builder()
                .client(client)
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiInterface::class.java)
    }
}