package com.woutervandervelde.e_cook.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.woutervandervelde.e_cook.data.dao.RecipeDao
import com.woutervandervelde.e_cook.domain.model.Recipe

@androidx.room.Database(
    entities = [
        Recipe::class
    ],
    version = 2 //TODO(Increment when changing database)
)

abstract class Database : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        fun create(context: Context): Database =
            Room.databaseBuilder(
                context.applicationContext,
                Database::class.java,
                "database.db"
            )
                .build()
    }
}