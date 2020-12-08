package com.example.event.ui.provider

import com.example.event.BuildConfig
import com.example.event.ui.network.EventApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StorageProvider {


    companion object {
        private const val BASE_URL = "https://api.github.com"

        fun provideEventApi(): EventApi {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().serializeNulls().create()
                    )
                )
                .build()

            return retrofit.create(EventApi::class.java)
        }
    }
}