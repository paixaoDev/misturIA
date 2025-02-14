package com.paixao.dev.misturia.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.paixao.dev.misturia.BuildConfig
import com.paixao.dev.misturia.domain.usecase.RequestRecipeToGeminiUseCase
import com.paixao.dev.misturia.presentation.state.HomeScreenState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

enum class Chefs (val prompt: String){
    Vozinha ("Você é um vó que esta fazendo uma receita para seu netinho"),
    Michelan("Você é um chef de cozinha criativo e especializado em criar receitas saborosas."),
    Cozinheiro("Você é um cozinheiro que faz receitas do dia a dia"),
    Caseiro ("Você é uma pessoa comum que buscar fazer receitas simples")
}

class HomeViewModel(
    val requestRecipeToGeminiUseCase: RequestRecipeToGeminiUseCase,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state

    fun generateRecipe(chef: Chefs = Chefs.Vozinha) {
        viewModelScope.launch {
            requestRecipeToGeminiUseCase.invoke(chef.prompt)
                .flowOn(coroutineDispatcher)
                .onStart { _state.value = _state.value.copy(isLoading = true) }
                .catch { }
                .collect { receipt ->
                    _state.value = _state.value.copy(
                        receipt = receipt
                    )
                }
        }
    }

    fun onConfigClick() {

    }

    fun onLikeClick() {

    }

    fun onTryAgainClick() {
        generateRecipe()
    }
}