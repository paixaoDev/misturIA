package com.paixao.dev.misturia.data.request


data class GeminiRequest(
    val content: List<Content> = listOf()
)

data class Content(
    val parts: List<Parts> = listOf()
)

data class Parts(
    val text: String = ""
)

fun GeminiRequest.createGeminiRequest(prompt: String): GeminiRequest {
    return GeminiRequest(
        content = listOf(
            Content(
                parts = listOf(
                    Parts(
                        text = prompt
                    )
                )
            )
        )
    )
}