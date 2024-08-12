package com.woutervandervelde.e_cook.domain.usecase

import android.content.Context
import java.io.File

object ClearCachedVideoAndFrames {
    fun invoke(videoFile: File, context: Context) {
        val framesDir = File(context.cacheDir, "frames")
        if (framesDir.exists()) framesDir.deleteRecursively()
        if (videoFile.exists()) videoFile.delete()
    }
}