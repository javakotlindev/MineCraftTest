package com.tellit.minecrafttest.repository

import com.tellit.minecrafttest.data.FavouritesDao
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val favouritesDao: FavouritesDao
) {
    fun addFavourites(favouritesModel: FavouritesModel) {
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDao.addFavourites(favouritesModel)

        }
    }

    fun getAllFavourites(): Flow<List<FavouritesModel>> {
        return favouritesDao.getAllFavourites()
    }

    fun update(status: Boolean, id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDao.update(status, id)
        }
    }

    fun isRowExist(id: String): Flow<Boolean> {
        return favouritesDao.isRowIsExist(id)
    }
}