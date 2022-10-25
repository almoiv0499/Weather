package com.example.data.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    val cityName: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
)