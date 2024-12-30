package com.paixao.dev.misturia.domain.entities

data class Recipe(
    val name: String = "",
    val ingredients: List<String> = listOf("", "")
)