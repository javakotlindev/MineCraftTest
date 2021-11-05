package com.tellit.minecrafttest.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tellit.minecrafttest.R
import com.tellit.minecrafttest.databinding.FragmentFavouritesBinding
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import com.tellit.minecrafttest.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private lateinit var adapter: FavouritesAdapter
    private lateinit var binding: FragmentFavouritesBinding
    private val viewModel: MainViewModel by viewModels()
    lateinit var data: ArrayList<FavouritesModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            data = ArrayList()
            adapter = FavouritesAdapter(requireContext())

            setData()
            adapter.setOnItemClickListener {
                viewModel.mainRepository.update(false, it.id)
                data.clear()
                adapter.notifyDataSetChanged()
            }
            modsRecyclerList.adapter = adapter
            modsRecyclerList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }

    private fun setData() {
        viewModel.getFavorites.observe(viewLifecycleOwner) {
            data.clear()
            for (i in it) {
                if (i.status) {
                    data.add(i)
                }
            }
            adapter.setDataAdapter(data)
        }
    }

}
