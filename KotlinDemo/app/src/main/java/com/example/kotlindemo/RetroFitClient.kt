package com.example.kotlindemo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

class RetroFitClient {

    companion object {
        private var retroFitClient: Retrofit? = null
        fun getRestClient(@Url url: String): Retrofit {
            if(retroFitClient == null) {
                val logger = HttpLoggingInterceptor()
                logger.apply { level = HttpLoggingInterceptor.Level.BODY }
                val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()
                retroFitClient = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
           return retroFitClient!!
        }
    }
}