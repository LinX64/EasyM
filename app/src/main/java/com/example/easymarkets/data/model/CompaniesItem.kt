package com.example.easymarkets.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CompaniesItem(
    @SerializedName("country")
    val country: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("properties")
    val properties: List<Property>,
    @SerializedName("url")
    val url: String
)