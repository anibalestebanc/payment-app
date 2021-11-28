package com.example.network.android.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class ApiClientFactory<T> {

    fun create(isDebug: Boolean, baseUrl: String, tClass: Class<T>): T {
        val okHttpClient = createOkHttpClient(isDebug)
        return createRetrofitClient(baseUrl, okHttpClient, tClass)
    }

    private fun createRetrofitClient(baseUrl: String, client: OkHttpClient, tClass: Class<T>): T {
        val moshiBuilder =
            Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder.build()))
            .client(client)
            .build()
        return retrofit.create(tClass)
    }

    private fun createOkHttpClient(
        isDebug: Boolean,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor(isDebug))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val level = if (isDebug) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().apply {
            setLevel(level = level)
        }
    }
}