package com.nicoduarte.articles.api

import com.nicoduarte.articles.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    companion object {
        private const val BASE_URL = "http://hn.algolia.com/api/v1/"
        private const val TIME_OUT = 15L

        private var apiRequest: ApiRequest? = null

        fun getInstance(): ApiRequest {
            if (apiRequest == null) {
                val httpClient = getOkHttpClient()

                val builder = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)

                apiRequest = builder.build().create(ApiRequest::class.java)

            }
            return apiRequest!!
        }

        private fun getOkHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) builder.addInterceptor(loggingInterceptor)

            return builder
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build()
        }
    }
}