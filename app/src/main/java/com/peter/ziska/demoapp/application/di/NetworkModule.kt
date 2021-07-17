package com.peter.ziska.demoapp.application.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.peter.ziska.demoapp.BuildConfig
import com.peter.ziska.demoapp.flows.data.service.NotinoApi
import com.peter.ziska.demoapp.flows.data.service.NotinoApiImpl
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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
            .baseUrl(BuildConfig.SERVER_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()

    @Singleton
    @Provides
    fun provideNotinoApi(
        retrofit: Retrofit,
    ): NotinoApi = NotinoApiImpl(retrofit)
}