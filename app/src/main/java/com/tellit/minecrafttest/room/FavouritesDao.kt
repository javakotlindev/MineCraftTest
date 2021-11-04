package com.tellit.minecrafttest.room

import androidx.room.*
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import kotlinx.coroutines.flow.Flow
import androidx.room.FtsOptions.Order

import androidx.room.Update




/**
 * Created by Aslbek on 03.11.2021.
 */
@Dao
interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourites(favouritesModel: FavouritesModel)

    @Query("SELECT * FROM favourites_table")
    fun getAllFavourites(): Flow<List<FavouritesModel>>

    @Query("UPDATE favourites_table SET status=:status WHERE id = :id")
    fun update(status: Boolean, id: Int)

    @Delete
    fun deleteFavoriteRecipe(favoritesModel: FavouritesModel)
}
