package com.tellit.minecrafttest.data

import androidx.room.*
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import kotlinx.coroutines.flow.Flow


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
    fun update(status: Boolean, id: String)

    @Delete
    fun deleteFavoriteRecipe(favoritesModel: FavouritesModel)

    @Query("SELECT EXISTS(SELECT * FROM favourites_table WHERE id = :id)")
    fun isRowIsExist(id: String): Flow<Boolean>
}
