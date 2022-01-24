package com.h.androidkotlinsample.di

import com.h.androidkotlinsample.BuildConfig
import com.h.androidkotlinsample.network.ApiServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * http 통신 모듈
 * */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val CONNECT_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L

    private var BASE_URL = "https://api.github.com"

    private fun provideOkHttpClient() = OkHttpClient.Builder().apply {
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)

        addInterceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder().apply {

                //header
                addHeader("Content-Type", "application/json")

                //body
                original.body
                method(original.method, original.body)
            }

            val request = builder.build()
            val response = chain.proceed(request)

            HttpLoggingInterceptor().apply {

            }

            response
        }

        addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        })
    }.build()

    @Singleton
    @Provides
    fun provideApiService(): ApiServiceInterface = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        addConverterFactory(GsonConverterFactory.create())
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        client(provideOkHttpClient())
    }.build().create(
        ApiServiceInterface::class.java
    )

}