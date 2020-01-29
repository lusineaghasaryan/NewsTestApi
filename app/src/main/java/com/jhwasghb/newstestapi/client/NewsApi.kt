package com.jhwasghb.newstestapi.client

import com.jhwasghb.newstestapi.models.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines?apiKey=7f9d3164178e40018134d06384e55b51")
    fun getNews(
        @Query("country")country:String
    ):Single<Response>
}