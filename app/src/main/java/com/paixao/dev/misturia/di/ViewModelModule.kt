package com.paixao.dev.misturia.di

import com.paixao.dev.misturia.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun provideHomeViewModel(
): HomeViewModel {
    return HomeViewModel()
}


val viewModelModule = module {
    viewModel { provideHomeViewModel() }
}