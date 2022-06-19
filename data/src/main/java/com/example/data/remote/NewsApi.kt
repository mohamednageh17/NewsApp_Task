package com.example.data.remote

import com.example.data.model.remote.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("search?")
    fun fetchNewsFeed(@Query("page") page: Int): Single<NewsResponse>
}