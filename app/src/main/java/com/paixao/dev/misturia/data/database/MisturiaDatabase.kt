package com.paixao.dev.misturia.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [IngredientEntity::class],
    version = 1
)

abstract class MisturiaDatabase: RoomDatabase(){
    abstract fun getDao(): MisturiaDao
}