package com.example.mha.news.model.currencymodel

import com.google.gson.annotations.SerializedName

data class Base(
    @SerializedName("success") val success: Boolean,
    @SerializedName("terms") val terms: String,
    @SerializedName("privacy") val privacy: String,
    @SerializedName("timestamp") val timestamp: Int,
    @SerializedName("source") val source: String,
    @SerializedName("quotes") val quotes: Quotes,
) {
}