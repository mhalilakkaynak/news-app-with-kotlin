package com.example.mha.news.service.dailynews

import android.content.Context
import com.example.mha.news.model.dailynewsmodel.Base
import com.example.mha.news.service.RetrofitClient
import retrofit2.Call

class DailynewsApiService {
    companion object {
        val BASE_URL = "http://newsapi.org/"
        val api = RetrofitClient.getClient(BASE_URL).create(DailynewsInterface::class.java)
    }
}