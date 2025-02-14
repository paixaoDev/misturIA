package com.paixao.dev.misturia.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val quantity: Int,
    val expiration: String
)