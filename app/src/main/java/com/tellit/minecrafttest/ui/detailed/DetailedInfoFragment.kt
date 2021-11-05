package com.tellit.minecrafttest.ui.detailed

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import com.tellit.minecrafttest.R
import com.tellit.minecrafttest.databinding.FragmentDetailedInfoBinding
import com.tellit.minecrafttest.ui.BaseMainFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class DetailedInfoFragment :
    BaseMainFragment<FragmentDetailedInfoBinding>(FragmentDetailedInfoBinding::inflate) {


    override fun onViewCreate() {
        binding.apply {
            titleTxt.text = requireArguments().getString("title")
            descriptionTxt.text = requireArguments().getString("description")
            setMainImage()
            setStatusFavourite()
            binding.backFragment.setOnClickListener {
                super.requireActivity().onBackPressed()
            }
        }
    }

    private fun setMainImage() {
        try {
            val assetManager: AssetManager = requireContext().assets

            assetManager.open("files/mods/${requireArguments().getString("imageUrl")}")
                .use { inputStream ->
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    binding.mainImage.setImageBitmap(bitmap)
                }
        } catch (ex: IOException) {
        }
    }

    private fun setStatusFavourite() {
        if (requireArguments().getBoolean("status")) {
            binding.checkImg.setImageResource(R.drawable.icon_favorites)
        } else {
            binding.checkImg.setImageResource(R.drawable.icon_unfavorites)
        }
    }

}

