package com.tellit.minecrafttest.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tellit.minecrafttest.model.favourites.FavouritesModel

@Database(
    entities = [FavouritesModel::class], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCardDao(): FavouritesDao
}

