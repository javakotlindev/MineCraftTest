package com.tellit.minecrafttest.ui.detailed

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.CountDownTimer
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tellit.minecrafttest.R
import com.tellit.minecrafttest.databinding.FragmentDetailedInfoBinding
import com.tellit.minecrafttest.ui.BaseMainFragment
import dagger.hilt.android.AndroidEntryPoint
import java.io.FileOutputStream
import java.io.IOException


@AndroidEntryPoint
class DetailedInfoFragment :
    BaseMainFragment<FragmentDetailedInfoBinding>(FragmentDetailedInfoBinding::inflate) {
    lateinit var countDownTimer: CountDownTimer
    private var isClicked = false
    private var location = ""

    override fun onViewCreate() {
        binding.apply {

            requestPermissions()

            titleTxt.text = requireArguments().getString("title")
            descriptionTxt.text = requireArguments().getString("description")
            setMainImage()
            setStatusFavourite()
            binding.backFragment.setOnClickListener {
                super.requireActivity().onBackPressed()
            }

            binding.downloadBtn.setOnClickListener {
                if (isClicked) {
                    isClicked = false

                    setFilesLocation()
                    copyDataBase()

                    binding.downloadBtn.text = getString(R.string.download)
                    try {
                        if (allPermissionsGranted()) {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK and
                                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                            intent.type = "file/*"
                            intent.data = Uri.parse(
                                "minecraft://?import=${location}"
                            )
                            startActivity(intent)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.minecraft_not_installed),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {
                    startTimer()
                    isClicked = true
                    binding.downloadBtn.isEnabled = false
                    binding.downloadBtn.text = getString(R.string.donwloading_in_proccess)
                }
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

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
                binding.downloadBtn.text = getString(R.string.install)
                binding.downloadBtn.isEnabled = true
            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }.start()
    }

    private fun allPermissionsGranted() = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ).all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            123
        )
    }

    @Throws(IOException::class)
    private fun copyDataBase() {

        val myOutput = FileOutputStream(location)
        val myInput =
            requireContext().assets.open("files/mods/${requireArguments().getString("fileName")}")

        val buffer = ByteArray(1024)
        var length: Int = myInput.read(buffer)
        while ((length) > 0) {
            myOutput.write(buffer, 0, length)
            length = myInput.read(buffer)
        }
        myInput.close()
        myOutput.flush()
        myOutput.close()
    }

    private fun setFilesLocation() {
        location =
            "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)}/${
                requireArguments().getString("fileName")
            }"
    }

    }

