package com.paixao.dev.misturia.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.paixao.dev.misturia.BuildConfig
import com.paixao.dev.misturia.domain.usecase.RequestRecipeToGeminiUseCase
import com.paixao.dev.misturia.presentation.state.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel() : ViewModel() {

    private val _state = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading())
    val state: StateFlow<HomeScreenUiState> = _state.asStateFlow()

    val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_KEY,
        generationConfig = generationConfig {
            temperature = 1f
            topK = 40
            topP = 0.95f
            maxOutputTokens = 8192
            responseMimeType = "text/plain"
        },
    )

    fun callGemini(ingredientsItemsList: String = "") {
        if (ingredientsItemsList.isBlank()) {
            Log.e("response", "Lista de Itens não pode ser branca")
            return
        }
        val prompt =
            "Crie uma receita de almoço completa e equilibrada, " +
                    "utilizando os seguintes ingredientes: ${ingredientsItemsList}. " +
                    "Priorize pratos que não tenham sido sugeridos anteriormente. " +
                    "Inclua instruções claras e o tempo aproximado de preparo."

        viewModelScope.launch {
            try {

                val response = generativeModel.generateContent(prompt)
                _state.value = HomeScreenUiState.Receipt(response.text ?: "")
            } catch (e: Exception) {
                Log.e("response", e.message ?: "Erro ao chamar a gemini")
            }
        }
    }
}