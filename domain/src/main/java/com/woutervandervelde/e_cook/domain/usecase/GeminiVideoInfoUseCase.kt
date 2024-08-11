package com.woutervandervelde.e_cook.domain.usecase

import android.util.Log
import com.google.ai.client.generativeai.type.content
import com.woutervandervelde.e_cook.domain.ai.GenerativeModel
import com.woutervandervelde.e_cook.domain.model.InstagramVideoInfo

object GeminiVideoInfoUseCase {
    suspend fun invoke(videoInfo: InstagramVideoInfo) {
        val model = GenerativeModel.getModel()
        val response = model.generateContent(
            content {
//                fileData(uri = "https://firebasestorage.googleapis.com/v0/b/e-cook-92f20.appspot.com/o/instagram_recipe_sample.mp4?alt=media&token=ae3dd994-f113-4aff-a595-71e0708f8542", mimeType = "video/mp4")
                fileData(videoInfo.thumbnail, mimeType = "image/jpg")
                text(videoInfo.description)
            }
        )

        Log.e("TAG", "$response")
    }
}