package com.paixao.dev.misturia.presentation.state

data class HomeScreenState(
    val isLoading: Boolean = false,
    val receipt: Receipt = Receipt()
)

data class Receipt(
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val receipt: String = "",
    val tags: List<String>? = null
)