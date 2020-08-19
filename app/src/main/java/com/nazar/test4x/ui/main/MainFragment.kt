package com.nazar.test4x.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.nazar.test4x.core.util.Resource
import com.nazar.test4x.core.util.autoCleared
import com.nazar.test4x.databinding.FragmentMainBinding
import com.nazar.test4x.ui.main.characterhighlightadapter.MainHighLightAdapter
import com.nazar.test4x.ui.main.characterrecommendedadapter.CharacterRecommendedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment :Fragment() {

    private var binding : FragmentMainBinding by autoCleared()

    private val viewModel : MainViewModel by viewModels()

    private lateinit var adapter : MainHighLightAdapter

    private lateinit var adapterRecommended : CharacterRecommendedAdapter
    private lateinit var adapterSporty : CharacterRecommendedAdapter
    private lateinit var adapterFashion : CharacterRecommendedAdapter
    private lateinit var adapterCasual : CharacterRecommendedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentMainBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
    }

    private fun setupRecyclerView() {
        adapter = MainHighLightAdapter()
        binding.recyclerViewHighlight.setHasFixedSize(true)
        binding.recyclerViewHighlight.adapter = adapter

        adapterRecommended = CharacterRecommendedAdapter()
        binding.recyclerViewRecommended.setHasFixedSize(true)
        binding.recyclerViewRecommended.adapter = adapterRecommended

        adapterSporty = CharacterRecommendedAdapter()
        binding.recyclerViewSporty.setHasFixedSize(true)
        binding.recyclerViewSporty.adapter = adapterSporty

        adapterFashion = CharacterRecommendedAdapter()
        binding.recyclerViewFashion.setHasFixedSize(true)
        binding.recyclerViewFashion.adapter = adapterFashion

        adapterCasual = CharacterRecommendedAdapter()
        binding.recyclerViewGame.setHasFixedSize(true)
        binding.recyclerViewGame.adapter = adapterCasual
    }

    private fun setupObserver(){
        viewModel.characterHighlights.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()){
                        adapter.item = it.data!!.subList(0,2)
                        adapterRecommended.itemList = it.data!!.subList(3,10)
                        adapterFashion.itemList =it.data!!.subList(4,10)
                        adapterSporty.itemList = it.data!!.subList(8,15)
                        adapterCasual.itemList = it.data!!.subList(10,18)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()

            }
        })
    }

}