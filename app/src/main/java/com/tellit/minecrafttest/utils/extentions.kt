package com.tellit.minecrafttest.utils

import android.content.Context
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.fragment.app.FragmentActivity


fun FragmentActivity.statusBarColor(
    @ColorInt statusBarColorX: Int,
    @ColorInt navigationBarColorX: Int,
    darkStatusBarTint: Boolean
) {
    val win = window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        statusBarColor = statusBarColorX
        navigationBarColor = navigationBarColorX
    }

    val dec = win.decorView
    if (darkStatusBarTint) {
        dec.systemUiVisibility = dec.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        dec.systemUiVisibility = 0
    }
}

fun Float.toPx(context: Context) = (this * context.resources.displayMetrics.scaledDensity + 0.5F)
