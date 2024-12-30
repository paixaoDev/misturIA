package com.paixao.dev.misturia.di

import com.paixao.dev.misturia.domain.repository.GeminiRepository
import com.paixao.dev.misturia.domain.usecase.RequestRecipeToGeminiUseCase
import org.koin.dsl.module

fun provideRequestRecipeToGeminiUseCase(
    repository: GeminiRepository
): RequestRecipeToGeminiUseCase {
    return RequestRecipeToGeminiUseCase(repository)
}

val useCaseModule = module {
    factory { provideRequestRecipeToGeminiUseCase(get()) }
}