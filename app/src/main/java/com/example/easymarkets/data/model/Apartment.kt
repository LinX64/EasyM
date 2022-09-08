package com.example.easymarkets.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Apartment(
    @SerializedName("area")
    val area: Int,
    @SerializedName("availableBedrooms")
    val availableBedrooms: List<AvailableBedroom>,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("rooms")
    val rooms: Int
)