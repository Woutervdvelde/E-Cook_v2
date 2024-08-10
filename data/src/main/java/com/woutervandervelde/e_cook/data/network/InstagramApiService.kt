package com.woutervandervelde.e_cook.data.network

import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InstagramApiService {
    @GET("video")
    fun getVideoInfo(@Query("url") url: String): Call<InstagramVideoInfo>
}