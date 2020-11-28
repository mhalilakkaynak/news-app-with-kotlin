package com.example.mha.news.model.weathermodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country") @Expose val country: String,
    @SerializedName("sunrise") @Expose val sunrise: Int,
    @SerializedName("sunset") @Expose val sunset: Int
) {}