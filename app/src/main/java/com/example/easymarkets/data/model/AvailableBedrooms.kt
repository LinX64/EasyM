package com.example.easymarkets.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AvailableBedrooms(
    @SerializedName("bedroom1")
    val bedroom1: Bedroom1,
    @SerializedName("bedroom2")
    val bedroom2: Bedroom1,
    @SerializedName("bedroom3")
    val bedroom3: Bedroom1
)