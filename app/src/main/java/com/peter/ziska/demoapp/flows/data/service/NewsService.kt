package com.peter.ziska.demoapp.flows.data.service

import com.peter.ziska.demoapp.BuildConfig
import com.peter.ziska.demoapp.flows.data.service.model.NewsRequestDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.*

interface NewsService {

    @GET("search")
    fun getNews(
        @Query("q") query: String,
        @Query("page") page: Int? = 1,
        @Query("page_size") pageSize: Int = 20,
        @Query("lang") lang: String = Locale.getDefault().language,
        @Header("x-rapidapi-key") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Header("x-rapidapi-host") host: String = BuildConfig.NEWS_HOST,
    ): Call<NewsRequestDto>
}
