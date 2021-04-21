package com.peter.ziska.demoapp.application.di

import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Named

@Module
class DateModule {

    @Provides
    fun provideLocale(): Locale = Locale.getDefault()

    @Provides
    @Named("DateFormat")
    fun provideDateFormat(locale: Locale): SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd", locale)
}