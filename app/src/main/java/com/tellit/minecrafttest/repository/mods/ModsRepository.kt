package com.tellit.minecrafttest.repository.mods

import android.util.Log
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import com.tellit.minecrafttest.room.FavouritesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ModsRepository @Inject constructor(
    private val favouritesDao: FavouritesDao
) {
    fun addFavourites(favouritesModel: FavouritesModel) {
        CoroutineScope(Dispatchers.IO).launch {
            favouritesDao.addFavourites(favouritesModel)

        }
    }
}