package com.paixao.dev.misturia.data.service


import com.paixao.dev.misturia.BuildConfig
import com.paixao.dev.misturia.data.request.GeminiRequest
import com.paixao.dev.misturia.domain.entities.Recipe
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GeminiApi {
    @POST(":generateContent")
    suspend fun generateRecipe(
        @Query("key") key: String = BuildConfig.GEMINI_KEY,
        @Body body: GeminiRequest
    ): Recipe
}