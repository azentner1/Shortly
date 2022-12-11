package com.urlshort.shortly.feature.home.data.api

import com.urlshort.shortly.feature.home.model.HomeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {

    @GET("shorten")
    fun shortenUrl(@Query("url") url: String): Call<HomeResponse>
}