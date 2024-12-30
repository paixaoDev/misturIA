package com.paixao.dev.misturia.domain.repository

import com.paixao.dev.misturia.domain.entities.Recipe

interface GeminiRepository {
    suspend fun requestRecipe(): Recipe
}