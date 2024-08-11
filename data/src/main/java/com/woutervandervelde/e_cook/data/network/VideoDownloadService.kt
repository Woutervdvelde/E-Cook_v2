package com.woutervandervelde.e_cook.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface VideoDownloadService {
    @GET
    fun downloadFile(@Url fileUrl: String): Call<ResponseBody>
}