package com.tellit.minecrafttest.ui.favourites

import android.content.Context
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tellit.minecrafttest.R
import com.tellit.minecrafttest.databinding.ItemFavouritesBinding
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException

class FavouritesAdapter(val context : Context) :
    RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {
    private var listener: ((FavouritesModel) -> Unit)? = null
    fun setOnItemClickListener(f: (FavouritesModel) -> Unit) {
        listener = f
    }

    var data = mutableListOf<FavouritesModel>()
    val assetManager: AssetManager = context.assets


    fun setDataAdapter(data: List<FavouritesModel>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }


    inner class FavouritesViewHolder(private val binding: ItemFavouritesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: FavouritesModel) {
            binding.apply {
                titleTxt.text = data.mkbgjd4
                descriptionTxt.text = data.mkbgji1

                try {
                    assetManager.open("files/mods/${data.mkbgjf2}").use { inputStream ->
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        mainImage.setImageBitmap(bitmap)
                    }
                } catch (ex: IOException) {
                }
                checkImg.setOnClickListener {
                    listener?.invoke(data)
                    checkImg.setImageResource(R.drawable.icon_favorites)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FavouritesViewHolder(
        ItemFavouritesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) =
        holder.bindData(data[position])

}