package com.example.recipefinder.di

import android.util.Log
import com.example.recipefinder.data.remote.SpoonacularApi
import com.example.recipefinder.data.repository.SpoonacularRepoImpl
import com.example.recipefinder.domain.repository.SpoonacularRepo
import com.example.recipefinder.domain.use_case.SearchUseCase
import com.example.recipefinder.domain.use_case.UseCases
import com.example.recipefinder.util.Constants.API_KEY
import com.example.recipefinder.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {



    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url.newBuilder().addQueryParameter("apiKey", API_KEY).build()
                Log.d("OkHttp", "New URL: $url")  // Add this line to debug the URL
                chain.proceed(request.newBuilder().url(url).build())
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(BASE_UR: String,okHttpClient: OkHttpClient) : SpoonacularApi {
        return  Retrofit.Builder()
            .baseUrl(BASE_UR)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesSpoonacularRepository(api : SpoonacularApi) : SpoonacularRepo {
        return SpoonacularRepoImpl(api)
    }


    @Provides
    @Singleton
    fun provideUseCases(repo : SpoonacularRepo) : UseCases {
        return UseCases(
            SearchUseCase(repo)
        )
    }

}