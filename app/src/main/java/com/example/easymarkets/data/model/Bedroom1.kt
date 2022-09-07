package com.example.easymarkets.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Bedroom1(
    @SerializedName("area")
    val area: Int,
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("beds")
    val beds: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)