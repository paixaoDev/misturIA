package com.paixao.dev.misturia.di

import com.paixao.dev.misturia.domain.usecase.RequestRecipeToGeminiUseCase
import com.paixao.dev.misturia.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private fun provideHomeViewModel(
    useCase: RequestRecipeToGeminiUseCase
): HomeViewModel {
    return HomeViewModel(useCase)
}


val viewModelModule = module {
    viewModel { provideHomeViewModel(get()) }
}