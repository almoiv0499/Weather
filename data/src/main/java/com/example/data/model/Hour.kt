package com.example.data.model

import com.google.gson.annotations.SerializedName

data class Hour(
    @SerializedName("condition")
    val description: Condition,
    @SerializedName("temp_c")
    val temperature: Double,
    @SerializedName("time")
    val time: String
)