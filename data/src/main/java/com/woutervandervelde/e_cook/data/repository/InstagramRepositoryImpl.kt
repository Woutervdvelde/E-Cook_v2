package com.woutervandervelde.e_cook.data.repository

import com.woutervandervelde.e_cook.data.network.InstagramApiService
import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo
import com.woutervandervelde.e_cook.domain.repository.InstagramRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InstagramRepositoryImpl @Inject constructor(
    private val instagramApiService: InstagramApiService
) : InstagramRepository {

    override suspend fun getVideoInfo(url: String): Result<InstagramVideoInfo> {
        return withContext(Dispatchers.IO) {
            try {
                val response = instagramApiService.getVideoInfo(url).execute()
                if (response.isSuccessful) {
                    response.body()?.let { Result.success(it) }
                        ?: Result.failure(Exception("No data"))
                } else {
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}