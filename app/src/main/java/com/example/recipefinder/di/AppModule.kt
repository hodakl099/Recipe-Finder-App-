package com.example.recipefinder.di

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

    @Provides
    @Singleton
    fun provideRetrofit(BASE_UR: String,okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideSpoonacular(retrofit: Retrofit) : SpoonacularApi {
        return retrofit.create(SpoonacularApi::class.java)
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