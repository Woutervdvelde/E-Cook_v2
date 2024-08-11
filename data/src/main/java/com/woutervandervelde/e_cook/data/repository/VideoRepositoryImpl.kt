package com.woutervandervelde.e_cook.data.repository

import android.content.Context
import com.woutervandervelde.e_cook.data.network.VideoDownloadService
import com.woutervandervelde.e_cook.domain.repository.VideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoDownloadService: VideoDownloadService,
    private val context: Context
) : VideoRepository {
    override suspend fun getVideoFromUrl(url: String): File? = withContext(Dispatchers.IO) {
        try {
            val response = videoDownloadService.downloadFile(url).execute()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    val videoFile = File(context.cacheDir, "recipe_video.mp4")
                    var inputStream: InputStream? = null
                    var outputStream: FileOutputStream? = null

                    try {
                        inputStream = body.byteStream()
                        outputStream = FileOutputStream(videoFile)

                        val buffer = ByteArray(4096)
                        var bytesRead: Int

                        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                            outputStream.write(buffer, 0, bytesRead)
                        }

                        outputStream.flush()
                        return@withContext videoFile
                    } finally {
                        inputStream?.close()
                        outputStream?.close()
                    }
                }
            }
            null
        } catch (e: Exception) {
            null
        }
    }
}