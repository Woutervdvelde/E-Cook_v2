package com.woutervandervelde.e_cook.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.woutervandervelde.e_cook.data.dao.RecipeDao
import com.woutervandervelde.e_cook.data.database.typeconverter.Converters
import com.woutervandervelde.e_cook.data.entity.RecipeEntity

@androidx.room.Database(
    entities = [
        RecipeEntity::class
    ],
    version = 1, //TODO(Increment when changing database)
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
//    ]
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        fun create(context: Context): Database =
            Room.databaseBuilder(
                context.applicationContext,
                Database::class.java,
                "database.db"
            ).build()
    }
}