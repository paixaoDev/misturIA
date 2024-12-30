package com.paixao.dev.misturia.data.repository

import com.paixao.dev.misturia.data.request.GeminiRequest
import com.paixao.dev.misturia.data.request.createGeminiRequest
import com.paixao.dev.misturia.data.service.GeminiApi
import com.paixao.dev.misturia.domain.entities.Recipe
import com.paixao.dev.misturia.domain.repository.GeminiRepository

class GeminiRepositoryImpl(
    val api: GeminiApi
) : GeminiRepository {

    override suspend fun requestRecipe(): Recipe {
        val prompt = "criar um receita para almoço escolhendo itens desta lista de ingredientes:"
        val result = api.generateRecipe(body = GeminiRequest().createGeminiRequest(prompt))

        return result
    }
}