package com.tellit.minecrafttest.ui.mods

import android.content.Context
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tellit.minecrafttest.R
import com.tellit.minecrafttest.databinding.ItemModsBinding
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException


class ModsAdapter(@ApplicationContext val context: Context) :
    RecyclerView.Adapter<ModsAdapter.ModsViewHolder>() {
    private var listener: ((String, Boolean) -> Unit)? = null
    fun setOnItemClickListener(f: (String, Boolean) -> Unit) {
        listener = f
    }

    private var rootOnClick: ((FavouritesModel) -> Unit)? = null
    fun rootOnClickListener(f: (FavouritesModel) -> Unit) {
        rootOnClick = f
    }

    private var dataList = ArrayList<FavouritesModel>()
    val assetManager: AssetManager = context.assets


    fun setDataAdapter(data: List<FavouritesModel>) {
        dataList.clear()
        dataList.addAll(data)
        this.notifyDataSetChanged()
    }


    inner class ModsViewHolder(private val binding: ItemModsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: FavouritesModel) {
            binding.apply {
                if (data.status) {
                    checkImg.setImageResource(R.drawable.icon_favorites)
                } else {
                    checkImg.setImageResource(R.drawable.icon_unfavorites)
                }


                try {
                    assetManager.open("files/mods/${data.mkbgjf2}").use { inputStream ->
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        mainImage.setImageBitmap(bitmap)
                    }
                } catch (ex: IOException) {
                }

                titleTxt.text = data.mkbgjd4
                descriptionTxt.text = data.mkbgji1
                checkImg.setOnClickListener {
                    if (data.status) {
                        dataList.clear()
                        listener?.invoke(data.id, false)
                        checkImg.setImageResource(R.drawable.icon_unfavorites)
                    } else {
                        dataList.clear()
                        listener?.invoke(data.id, true)
                        checkImg.setImageResource(R.drawable.icon_favorites)
                    }
                }
                root.setOnClickListener {
                    rootOnClick?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ModsViewHolder(
        ItemModsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ModsViewHolder, position: Int) =
        holder.bindData(dataList[position])

}
