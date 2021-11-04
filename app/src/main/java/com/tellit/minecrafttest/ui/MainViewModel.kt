package com.tellit.minecrafttest.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.gson.Gson
import com.tellit.minecrafttest.model.mods.ModsData
import com.tellit.minecrafttest.repository.favourites.FavouritesRepository
import com.tellit.minecrafttest.repository.mods.ModsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    var favouritesRepository: FavouritesRepository,
    var modsRepository: ModsRepository
) : ViewModel() {

    val getFavorites = favouritesRepository.getAllFavourites().asLiveData()


    fun getDataFromJSON(): ModsData {
        lateinit var list: ModsData

        try {
            val gson = Gson()
            val json = getJsonFromAssets(context, "content.json")
            list = gson.fromJson(json, ModsData::class.java)

        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    private fun getJsonFromAssets(context: Context, fileName: String?): String? {
        return try {
            val location: InputStream = context.assets.open(fileName.toString())
            val size: Int = location.available()
            val buffer = ByteArray(size)
            location.read(buffer)
            location.close()
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }


}
