package com.example.newsapi
import com.example.newsapi.Model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?apiKey=4a1a31e70f7143ba85916709220212ea")
    fun getNews(
        @Query("country") countryCode: String
    ): Call<Data>

    @GET("top-headlines?apiKey=4a1a31e70f7143ba85916709220212ea")
    fun getCategoryNews(
        @Query("country") countryCode: String,
        @Query("category") category: String,
    ):Call<Data>
}


