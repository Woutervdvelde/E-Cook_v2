package com.woutervandervelde.e_cook.domain.usecase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.arthenica.ffmpegkit.FFmpegKit
import java.io.File

object ExtractVideoFramesUseCase {
    fun invoke(videoFile: File, context: Context): List<Bitmap> {
        val bitmaps = mutableListOf<Bitmap>()
        val framesDir = File(context.cacheDir, "frames")
        if (!framesDir.exists()) framesDir.mkdirs()

        val framesOutputPath = "${framesDir.absolutePath}/frame_%04d.jpg"
        val cmd = "-i ${videoFile.absolutePath} -vf fps=1 $framesOutputPath"

        FFmpegKit.execute(cmd)

        framesDir.listFiles()?.forEach { frame ->
            val bitmap = BitmapFactory.decodeFile(frame.absolutePath)
            bitmaps.add(bitmap)
        }

        return bitmaps
    }
}