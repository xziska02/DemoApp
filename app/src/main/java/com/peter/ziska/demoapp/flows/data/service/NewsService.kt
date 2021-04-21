package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.BuildConfig
import com.peter.ziska.demoapp.flows.data.model.NewsRequestDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    fun getNews(
        @Query("q") query: String,
        @Query("from") dateFrom: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
    ): Call<NewsRequestDto>
}
