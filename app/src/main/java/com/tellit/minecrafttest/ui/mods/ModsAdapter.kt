package com.tellit.minecrafttest.ui.mods

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tellit.minecrafttest.R
import com.tellit.minecrafttest.databinding.ItemModsBinding
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import com.tellit.minecrafttest.model.mods.ModsInfoData


class ModsAdapter :
    RecyclerView.Adapter<ModsAdapter.ModsViewHolder>() {
    private var listener: ((ModsInfoData, Int) -> Unit)? = null
    fun setOnItemClickListener(f: (ModsInfoData, Int) -> Unit) {
        listener = f
    }

    private var data = ArrayList<ModsInfoData>()
    lateinit var favouritesModel: List<FavouritesModel>


    fun setDataAdapter(data: ArrayList<ModsInfoData>, favouritesModel: List<FavouritesModel>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
        this.favouritesModel = favouritesModel
    }


    inner class ModsViewHolder(private val binding: ItemModsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ModsInfoData) {
            binding.apply {
                Log.i("uuuuuuuuuuuuu", "bindData: $favouritesModel")

                titleTxt.text = data.mkbgjd4
                descriptionTxt.text = data.mkbgji1
                checkImg.setOnClickListener {
                    checkImg.setImageResource(R.drawable.icon_favorites)
                    listener?.invoke(data, 2)
            }
        }
    }
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ModsViewHolder(
    ItemModsBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    )
)

override fun getItemCount() = data.size

override fun onBindViewHolder(holder: ModsViewHolder, position: Int) =
    holder.bindData(data[position])

}