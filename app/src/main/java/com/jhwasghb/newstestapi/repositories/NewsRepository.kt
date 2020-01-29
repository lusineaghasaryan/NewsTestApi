package com.jhwasghb.newstestapi.repositories

import com.jhwasghb.newstestapi.client.ApiClient
import com.jhwasghb.newstestapi.client.NewsApi
import com.jhwasghb.newstestapi.models.Response
import io.reactivex.Single

class NewsRepository() {
    fun getAllNews(country: String):Single<Response> = ApiClient.get().create(NewsApi::class.java).getNews(country)
}