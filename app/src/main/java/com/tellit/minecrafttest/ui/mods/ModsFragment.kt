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
import com.tellit.minecrafttest.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModsFragment : Fragment(R.layout.fragment_mods) {
    lateinit var adapter: ModsAdapter
    private lateinit var binding: FragmentModsBinding
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
            adapter = ModsAdapter(requireContext())

            initRecyclerView()

            modsRecyclerList.adapter = adapter
            modsRecyclerList.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapterOnclick()
        }


    }

    private fun initRecyclerView() {
        viewModel.getFavorites.observe(viewLifecycleOwner) {
            adapter.setDataAdapter(it)
        }
    }

    private fun adapterOnclick() {
        adapter.setOnItemClickListener { id, status ->
            viewModel.mainRepository.update(status, id)
        }
    }


}