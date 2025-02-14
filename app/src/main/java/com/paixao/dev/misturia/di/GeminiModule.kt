package com.paixao.dev.misturia.di

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.paixao.dev.misturia.BuildConfig
import org.koin.dsl.module

private fun provideGenerativeModel() =
    GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_KEY,
        generationConfig = generationConfig {
            temperature = 0.7f // Ajuste para mais criatividade
        },
        safetySettings = listOf(
            // Configurações de segurança padrão :cite[7]
        )
    )

val geminiModule = module {
    provideGenerativeModel()
}