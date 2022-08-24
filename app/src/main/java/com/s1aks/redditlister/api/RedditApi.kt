package com.s1aks.redditlister.api

import android.util.Log
import com.s1aks.redditlister.data.ListingResponse
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {

    @GET("/r/popular/hot.json")
    suspend fun getPopularTop(
        @Query("limit") limit: Int,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): ListingResponse

    companion object {
        private const val BASE_URL = "https://www.reddit.com/"
        fun create(): RedditApi {
            val logger = HttpLoggingInterceptor { Log.d("API", it) }
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(HttpUrl.parse(BASE_URL)!!)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RedditApi::class.java)
        }
    }


}
