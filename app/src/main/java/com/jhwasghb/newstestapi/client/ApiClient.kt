package com.jhwasghb.newstestapi.client

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        val base_URL = "https://newsapi.org/"

        fun get(): Retrofit{
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(base_URL)
                .build()
        }
    }
}