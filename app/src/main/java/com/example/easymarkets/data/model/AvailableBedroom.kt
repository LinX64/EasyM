package com.example.easymarkets.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.util.*

@Keep
data class AvailableBedroom(
    @SerializedName("area")
    val area: Int,
    @SerializedName("available")
    val available: Boolean,
    @SerializedName("availableDate")
    val availableDate: String?,
    @SerializedName("beds")
    val beds: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)