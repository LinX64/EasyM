package com.example.easymarkets.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.easymarkets.R
import com.example.easymarkets.data.model.Apartment
import com.example.easymarkets.databinding.FragmentBlankBinding
import com.example.easymarkets.view.adapter.MainAdapter
import com.example.easymarkets.view.viewmodel.MainViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@AndroidEntryPoint
class BlankFragment : Fragment(R.layout.fragment_blank) {

    private lateinit var binding: FragmentBlankBinding
    private val mainViewModel by activityViewModels<MainViewModel>()

    private lateinit var mAdapter: MainAdapter
    private var apartments: List<Apartment> = listOf()

    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    private lateinit var customAlertDialogView: View

    private val sdf = SimpleDateFormat("MM-dd-yyyy")

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
        setupFabButton()
    }

    private fun setupFabButton() {
        binding.floatingActionButton.setOnClickListener { handleDateFilter() }
    }

    private fun handleDateFilter() {
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
        customAlertDialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.material_date_dialog, null, false)

        launchCustomDialog()
    }

    private fun launchCustomDialog() {
        val startDateTxt =
            customAlertDialogView.findViewById(R.id.startDateEditText) as TextInputEditText
        val endDateTxt =
            customAlertDialogView.findViewById(R.id.endDateEditText) as TextInputEditText

        materialAlertDialogBuilder.setView(customAlertDialogView)
            .setTitle("Filter by date")
            .setMessage("Enter your desired date")
            .setPositiveButton("Search") { dialog, _ ->

                convertTextToDate(startDateTxt, endDateTxt)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun convertTextToDate(
        startDateTxt: TextInputEditText,
        endDateTxt: TextInputEditText
    ) {
        try {

            val startDate = sdf.parse(startDateTxt.text.toString())
            val endDate = sdf.parse(endDateTxt.text.toString())

            filterBasedOnStartEndDate(startDate, endDate)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupSearchView() {
        binding.searchView.apply {
            queryHint = "Search"

            setOnQueryTextListener()
        }
    }

    private fun SearchView.setOnQueryTextListener() {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterBasedOnInput(newText)
                return true
            }
        })
    }

    private fun setupObserver() {
        mainViewModel.loadData().observe(viewLifecycleOwner) { companies ->
            companies[0].properties.forEach { properties ->
                val getApartments = properties.apartments

                apartments = getApartments
                renderData(getApartments)
            }
        }
    }

    private fun filterBasedOnInput(newText: String?) {
        val filteredList = apartments.filter { apartment ->
            apartment.name.contains(newText.toString(), ignoreCase = true)
        }
        renderData(filteredList)
    }

    private fun filterBasedOnStartEndDate(startDate: Date?, endDate: Date?) {
        apartments.forEach { apartment ->

            apartment.availableBedrooms.forEach { bedroom ->
                bedroom.availableDate?.filter { date ->
                    val mDate = sdf.parse(date.toString())

                    mDate?.after(startDate) == true && mDate.before(endDate)
                }
                renderData(apartments)
            }
        }
    }

    private fun renderData(properties: List<Apartment>) {
        mAdapter.setData(properties)
    }
}