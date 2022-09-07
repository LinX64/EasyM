package com.example.easymarkets.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Property(
    @SerializedName("apartments")
    val apartments: List<Apartment>
)