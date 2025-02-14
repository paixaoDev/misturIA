package com.paixao.dev.misturia.domain.repository

import com.paixao.dev.misturia.presentation.state.Receipt

interface GeminiRepository {
    suspend fun generateRecipe(chefPrompt: String, ingredients: List<String>): Receipt
}