package com.paixao.dev.misturia.di

import com.google.gson.GsonBuilder
import com.paixao.dev.misturia.data.service.GeminiApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private fun provideRetrofit(
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .baseUrl("https://generativelanguage.googleapis.com/")
        .build()
}

private fun provideConverterFactory(): GsonConverterFactory {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return GsonConverterFactory.create(gson)
}


private fun provideService(retrofit: Retrofit): GeminiApi =
    retrofit.create(GeminiApi::class.java)

val networkModule = module {
    single { provideConverterFactory() }
    single { provideRetrofit(get()) }
    single { provideService(get()) }
}