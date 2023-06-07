package com.example.recipefinder.di

import com.example.recipefinder.util.Constants.API_KEY
import com.example.recipefinder.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {



    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideOkhttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
               level = HttpLoggingInterceptor.Level.BODY
            }
            )
            .addInterceptor{ chains->
                val request = chains.request()
                val url = request.url.newBuilder().addQueryParameter("apikey",API_KEY).build()
                chains.proceed(request.newBuilder().url(url).build())
            }.build()
    }



}