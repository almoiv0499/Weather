package com.example.data.model

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text")
    val description: String,
    @SerializedName("icon")
    val icon: String
)