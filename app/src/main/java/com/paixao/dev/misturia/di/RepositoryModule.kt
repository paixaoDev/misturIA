package com.paixao.dev.misturia.di

import com.paixao.dev.misturia.data.repository.GeminiRepositoryImpl
import com.paixao.dev.misturia.data.service.GeminiApi
import com.paixao.dev.misturia.domain.repository.GeminiRepository
import org.koin.dsl.module

fun provideGeminiRepository(
    api: GeminiApi
): GeminiRepository {
    return GeminiRepositoryImpl(api)
}

val repositoryModule = module {
    factory { provideGeminiRepository(get()) }
}