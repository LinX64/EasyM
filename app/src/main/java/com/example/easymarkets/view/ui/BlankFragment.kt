package com.example.easymarkets.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.easymarkets.R
import com.example.easymarkets.data.model.Apartment
import com.example.easymarkets.databinding.FragmentBlankBinding
import com.example.easymarkets.view.adapter.MainAdapter
import com.example.easymarkets.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class BlankFragment : Fragment(R.layout.fragment_blank) {

    private lateinit var binding: FragmentBlankBinding
    private val mainViewModel by activityViewModels<MainViewModel>()

    private lateinit var mAdapter: MainAdapter
    private var apartments: List<Apartment> = listOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlankBinding.bind(view)

        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        mAdapter = MainAdapter()
        binding.recyclerView.adapter = mAdapter

        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchView.apply {
            queryHint = "Search"
            isIconified = false

            setOnQueryTextListener()
        }
    }

    private fun SearchView.setOnQueryTextListener() {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })
    }

    private fun setupObserver() {
        mainViewModel.loadData().observe(viewLifecycleOwner) { companies ->
            companies[0].properties.forEach { properties ->
                val getProperties = properties.apartments
                // we just want to show the first apartment
                apartments = getProperties
                renderData(getProperties)
            }
        }
    }

    private fun filter(newText: String?) {
        val filteredList = apartments.filter { apartment ->
            apartment.name.contains(newText.toString(), ignoreCase = true)
        }
        renderData(filteredList)
    }

    private fun renderData(properties: List<Apartment>) {
        mAdapter.setData(properties)
    }
}