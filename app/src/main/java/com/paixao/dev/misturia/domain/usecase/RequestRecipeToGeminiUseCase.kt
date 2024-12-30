package com.paixao.dev.misturia.domain.usecase

import com.paixao.dev.misturia.domain.entities.Recipe
import com.paixao.dev.misturia.domain.repository.GeminiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RequestRecipeToGeminiUseCase(
    private val repository: GeminiRepository
) {
    operator fun invoke(): Flow<Recipe> {
        return flow {
            emit(repository.requestRecipe())
        }
    }
}