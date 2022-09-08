package com.example.easymarkets.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.easymarkets.data.model.Companies
import com.example.easymarkets.data.repository.MainRepository
import com.example.easymarkets.util.Util.Companion.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mApp: Application,
    private val mainRepository: MainRepository
) : AndroidViewModel(mApp) {

    fun loadData() = liveData(Dispatchers.IO) {
        val mData = getJsonDataFromAsset(mApp, "file.json")

        val listCompaniesType = object : TypeToken<Companies>() {}.type
        val companies: Companies = Gson().fromJson(mData, listCompaniesType)

        emit(companies)
    }
}