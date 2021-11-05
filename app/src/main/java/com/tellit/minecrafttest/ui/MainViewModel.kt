package com.tellit.minecrafttest.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.gson.Gson
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import com.tellit.minecrafttest.model.mods.ModsData
import com.tellit.minecrafttest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject
import android.graphics.BitmapFactory

import android.graphics.Bitmap

import android.content.res.AssetManager




@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    var mainRepository: MainRepository
) : ViewModel() {

    val getFavorites = mainRepository.getAllFavourites().asLiveData()

    fun isRowExist(id : String) : LiveData<Boolean>{
        return  mainRepository.isRowExist(id).asLiveData()
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

    companion object {
        fun getDataFromJSON(mainViewModel: MainViewModel): ModsData {
            lateinit var list: ModsData

            try {
                val gson = Gson()
                val json = mainViewModel.getJsonFromAssets(mainViewModel.context, "content.json")
                list = gson.fromJson(json, ModsData::class.java)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return list
        }
    }



}
