package com.tellit.minecrafttest.ui.viewpager

import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.tellit.minecrafttest.R
import com.tellit.minecrafttest.databinding.FragmentViewPagerBinding
import com.tellit.minecrafttest.ui.BaseMainFragment
import com.tellit.minecrafttest.ui.favourites.FavouritesFragment
import com.tellit.minecrafttest.ui.mods.ModsFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewPagerFragment :
    BaseMainFragment<FragmentViewPagerBinding>(FragmentViewPagerBinding::inflate) {

    private lateinit var orderFragments: ArrayList<Fragment>
    private lateinit var adapter: ViewPagerAdapter
    private val notificationId = 102


    override fun onViewCreate() {
        setHasOptionsMenu(true)
        addFragments()
        adapter = ViewPagerAdapter(this, orderFragments)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> {
                    getString(R.string.mods_w_dots)
                }
                else -> {
                    getString(R.string.favourites)
                }

            }

        }.attach()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab!!.text = "•${tab.text}•"
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.text = tab.text.toString().replace("•", "")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewPager.currentItem =
            if (arguments?.getInt("TYPE") == null) 0 else arguments?.getInt("TYPE")!!

    }


    private fun addFragments() {
        orderFragments = ArrayList()
        orderFragments.add(ModsFragment())
        orderFragments.add(FavouritesFragment())

    }



}
