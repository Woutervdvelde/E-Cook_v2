package com.woutervandervelde.e_cook.domain.usecase

import android.graphics.Bitmap
import com.google.ai.client.generativeai.type.content
import com.woutervandervelde.e_cook.domain.ai.GenerativeModel
import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo

object GetGeminiVideoInfoUseCase {
    suspend fun invoke(videoInfo: InstagramVideoInfo, frames: List<Bitmap>): String {
        val model = GenerativeModel.getModel()
        val response = model.generateContent(
            content {
                frames.forEach { image(it) }
                text(videoInfo.description)
            }
        )
        return response.text ?: ""
    }
}