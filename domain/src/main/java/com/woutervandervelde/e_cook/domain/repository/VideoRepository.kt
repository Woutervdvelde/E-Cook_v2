package com.woutervandervelde.e_cook.domain.repository

import java.io.File

interface VideoRepository {
    suspend fun getVideoFromUrl(url: String): File?
}