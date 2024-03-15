package com.zekierciyas.github_app.core.di

import com.zekierciyas.github_app.core.data.api.GithubAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(url: Url): Retrofit {
        val okHttpClientBuilder = OkHttpClient.Builder()

        // Add logging interceptor only in debug mode
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Set logging level to BODY for full raw response logging
        }
        okHttpClientBuilder.addInterceptor(loggingInterceptor)

        val okHttpClient = okHttpClientBuilder.build()

        return Retrofit.Builder()
            .baseUrl(url.getUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserNetwork(
        retrofit: Retrofit
    ): GithubAPI = retrofit.create(GithubAPI::class.java)

}