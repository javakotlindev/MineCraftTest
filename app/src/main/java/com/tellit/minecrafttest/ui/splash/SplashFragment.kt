package com.tellit.minecrafttest.ui.splash

import android.os.CountDownTimer
import androidx.fragment.app.viewModels
import com.tellit.minecrafttest.R
import com.tellit.minecrafttest.databinding.FragmentSplashBinding
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import com.tellit.minecrafttest.ui.BaseMainFragment
import com.tellit.minecrafttest.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseMainFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    lateinit var countDownTimer: CountDownTimer
    private val viewModel: MainViewModel by viewModels()


    override fun onViewCreate() {
        addJsonToDB()
        startTimer()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                navController.navigate(R.id.splash_to_viewPager)
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }

    private fun addJsonToDB() {
        for (key in MainViewModel.getDataFromJSON(viewModel).mkbgj_list5) {
            viewModel.isRowExist(key.key).observe(viewLifecycleOwner) {
                if (!it) {
                    viewModel.mainRepository.addFavourites(
                        FavouritesModel(
                            key.key,
                            key.value.mkbgj_pw5,
                            key.value.mkbgjt3,
                            key.value.mkbgj_ieq,
                            key.value.mkbgji1,
                            key.value.mkbgjd4,
                            key.value.mkbgjf2
                        )
                    )
                }
            }
        }

    }
}