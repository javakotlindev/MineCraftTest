package com.tellit.minecrafttest.ui.mods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tellit.minecrafttest.R
import com.tellit.minecrafttest.databinding.FragmentModsBinding
import com.tellit.minecrafttest.model.favourites.FavouritesModel
import com.tellit.minecrafttest.model.mods.ModsData
import com.tellit.minecrafttest.model.mods.ModsInfoData
import com.tellit.minecrafttest.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModsFragment : Fragment(R.layout.fragment_mods) {
    lateinit var adapter: ModsAdapter
    private lateinit var dataList: ArrayList<ModsInfoData>
    private lateinit var binding: FragmentModsBinding
    var checkStr: String = ""
    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            adapter = ModsAdapter()
            addData(viewModel.getDataFromJSON())
            initRecyclerView(dataList)

            modsRecyclerList.adapter = adapter
            modsRecyclerList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            adapterOnclick()
        }


    }

    private fun addData(data: ModsData) {
        dataList = ArrayList()
        for (key in data.mkbgj_list5) {
            dataList.add(key.value)
        }

    }


    private fun initRecyclerView(data: ArrayList<ModsInfoData>) {
        viewModel.getFavorites.observe(viewLifecycleOwner) {
            adapter.setDataAdapter(data, it)
        }
    }

    private fun adapterOnclick() {
        adapter.setOnItemClickListener { it, status ->
            if (status == 1) {
                viewModel.favouritesRepository.deleteFavoriteRecipe(
                    FavouritesModel(
                        0,
                        it.mkbgj_pw5,
                        it.mkbgjt3,
                        it.mkbgj_ieq,
                        it.mkbgji1,
                        it.mkbgjd4,
                        it.mkbgjf2
                    )
                )
            } else {
                viewModel.modsRepository.addFavourites(
                    FavouritesModel(
                        0,
                        it.mkbgj_pw5,
                        it.mkbgjt3,
                        it.mkbgj_ieq,
                        it.mkbgji1,
                        it.mkbgjd4,
                        it.mkbgjf2
                    )
                )
            }
        }
    }


}