package com.paixao.dev.misturia.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.google.gson.GsonBuilder
import com.paixao.dev.misturia.BuildConfig
import com.paixao.dev.misturia.domain.repository.GeminiRepository
import com.paixao.dev.misturia.extensions.jsonClean
import com.paixao.dev.misturia.presentation.state.Receipt

class GeminiRepositoryImpl : GeminiRepository {

    private val generativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = BuildConfig.GEMINI_KEY,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                temperature = 0.7f // Ajuste para mais criatividade
            },
            safetySettings = listOf(
                // Configurações de segurança padrão :cite[7]
            )
        )
    }

    private val chat = generativeModel.startChat()

    override suspend fun generateRecipe(chefPrompt: String,ingredients: List<String>): Receipt {
        val prompt = """
            $chefPrompt
            Gere uma receita escolhendo uma proteina das possibilidades da minha lista de ingredientes: ${ingredients.joinToString()}.
            Formato exigido:
            {
                "name": "Nome da receita",
                "description": "Descrição com 220 caracteres",
                "image": "URL de imagem (opcional)
                "receipt": "Instruções em HTML, incluir métodos de preparo, tempo de cozimento",
                "tags": ["tag1", "tag2"]
            }
            Não inclua markdown. Use apenas HTML simples para 'receipt'.
        """.trimIndent()

        val response = chat.sendMessage(prompt)
        return parseGeminiResponse(response.text)
    }

    private fun parseGeminiResponse(json: String?): Receipt {
        val gson = GsonBuilder().create()
        return json?.let { js ->
            gson.fromJson(
                js.jsonClean(),
                Receipt::class.java
            )
        } ?: Receipt()
    }
}