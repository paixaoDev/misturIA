package com.paixao.dev.misturia.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.paixao.dev.misturia.BuildConfig
import com.paixao.dev.misturia.domain.usecase.RequestRecipeToGeminiUseCase
import com.paixao.dev.misturia.presentation.state.HomeScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel() : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_KEY
    )

    val chat = generativeModel.startChat()

    fun callGemini(ingredientsItemsList: String = "") {
        _state.value = _state.value.copy(isLoading = true)

        if (ingredientsItemsList.isBlank()) {
            Log.e("response", "Lista de Itens não pode ser branca")
            return
        }
        val prompt =
            "Você é um chef de cozinha criativo e especializado em criar receitas saborosas. " +
                    "Abaixo está uma lista de ingredientes que tenho em casa. " +
                    "Por favor, crie uma unica receita que seja única e saborosa escolhendo uma proteina das possibilidades da minha lista de ingredientes, " +
                    "garantindo que você não repita o ingrediente principal em nenhuma das receitas que você criar. " +
                    "As sugestões de receitas devem ser variadas e incluir métodos de preparo, tempo de cozimento e dicas de apresentação." +
                    "Aqui estão os ingredientes que eu tenho ${ingredientsItemsList}. "


        viewModelScope.launch {
            try {
                val response = chat.sendMessage(prompt)
                _state.value = _state.value.copy(isLoading = false, receipt = response.text ?: "")
            } catch (e: Exception) {
                Log.e("response", e.message ?: "Erro ao chamar a gemini")
            }
        }
    }
}