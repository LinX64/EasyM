package com.example.easymarkets.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Property(
    @SerializedName("apartments")
    val apartments: List<Apartment>
)