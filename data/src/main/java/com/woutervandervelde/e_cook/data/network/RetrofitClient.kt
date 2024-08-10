package com.woutervandervelde.e_cook.data.network

import com.woutervandervelde.e_cook.data.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var client: Retrofit? = null

    fun getClient(): Retrofit {
        if (client == null) {
            client = Retrofit.Builder()
                .baseUrl(BuildConfig.INSTAGRAM_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return client!!
    }
}