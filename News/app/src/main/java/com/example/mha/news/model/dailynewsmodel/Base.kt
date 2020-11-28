package com.example.mha.news.model.dailynewsmodel

import com.google.gson.annotations.SerializedName

data class Base(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Articles>,
) {
}