package com.paixao.dev.misturia.di

import com.google.ai.client.generativeai.GenerativeModel
import com.paixao.dev.misturia.data.repository.GeminiRepositoryImpl
import com.paixao.dev.misturia.data.service.GeminiApi
import com.paixao.dev.misturia.domain.repository.GeminiRepository
import org.koin.dsl.module

private fun provideGeminiRepository(

): GeminiRepository {
    return GeminiRepositoryImpl()
}

val repositoryModule = module {
    factory { provideGeminiRepository() }
}