package com.tellit.minecrafttest.ui.splash

import android.os.CountDownTimer
import androidx.fragment.app.viewModels
import com.tellit.minecrafttest.R
import com.tellit.minecrafttest.databinding.FragmentSplashBinding
import com.tellit.minecrafttest.ui.BaseMainFragment
import com.tellit.minecrafttest.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseMainFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    lateinit var countDownTimer: CountDownTimer
    private val viewModel: MainViewModel by viewModels()


    override fun onViewCreate() {
        viewModel.addJsonToDB()
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


}