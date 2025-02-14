package com.paixao.dev.misturia.domain.usecase

import com.paixao.dev.misturia.domain.repository.GeminiRepository
import com.paixao.dev.misturia.presentation.state.Receipt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RequestRecipeToGeminiUseCase(
    private val repository: GeminiRepository
) {
    operator fun invoke(chefPrompt: String): Flow<Receipt> {
        return flow {
            emit(
                repository.generateRecipe(
                    chefPrompt = chefPrompt,
                    listOf(
                        "Arroz",
                        "Cebola",
                        "Feijão",
                        "Picanha",
                        "Frango",
                        "Temperos Variados"
                    )
                )
            )
        }
    }
//    val prompt =
//            "Você é um chef de cozinha criativo e especializado em criar receitas saborosas. " +
//                    "Abaixo está uma lista de ingredientes que tenho em casa. " +
//                    "Por favor, crie uma unica receita que seja única e saborosa escolhendo uma proteina das possibilidades da minha lista de ingredientes, " +
//                    "garantindo que você não repita o ingrediente principal em nenhuma das receitas que você criar. " +
//                    "As sugestões de receitas devem ser variadas e incluir métodos de preparo, tempo de cozimento e dicas de apresentação." +
//                    "Aqui estão os ingredientes que eu tenho ${ingredientsItemsList}. "
}