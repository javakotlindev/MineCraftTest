package com.tellit.minecrafttest.repository.favourites

import com.tellit.minecrafttest.model.favourites.FavouritesModel
import com.tellit.minecrafttest.room.FavouritesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesRepository @Inject constructor(
    private val favouritesDao: FavouritesDao
) {

    fun getAllFavourites(): Flow<List<FavouritesModel>> {
        return favouritesDao.getAllFavourites()
    }

    fun deleteFavoriteRecipe(favouritesModel: FavouritesModel) {
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDao.deleteFavoriteRecipe(favouritesModel)
        }

    }
}