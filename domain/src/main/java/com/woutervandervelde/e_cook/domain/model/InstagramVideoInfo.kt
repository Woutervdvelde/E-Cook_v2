package com.woutervandervelde.e_cook.domain.model

import com.google.gson.annotations.SerializedName

data class InstagramVideoInfo(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("videoUrl") val videoUrl: String,
    @SerializedName("source") val source: String
)