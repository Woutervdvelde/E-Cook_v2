package com.woutervandervelde.e_cook.domain.repository

import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo

interface InstagramRepository {
    suspend fun getVideoInfo(url: String): Result<InstagramVideoInfo>
}