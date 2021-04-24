package com.peter.ziska.demoapp.application.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.peter.ziska.demoapp.BuildConfig
import com.peter.ziska.demoapp.flows.data.service.NewsApi
import com.peter.ziska.demoapp.flows.data.service.NewsApiImpl
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {

        val builder =
            OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    @Provides
    fun providesNonStrictJson() = Json {
        isLenient = true
        ignoreUnknownKeys = true
        allowSpecialFloatingPointValues = true
        useArrayPolymorphism = true
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_SERVER_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()

    @Singleton
    @Provides
    fun provideNewsApi(
        retrofit: Retrofit,
    ): NewsApi = NewsApiImpl(retrofit)
}