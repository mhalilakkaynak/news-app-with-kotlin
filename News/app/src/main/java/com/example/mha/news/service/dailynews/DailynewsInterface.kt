package com.example.mha.news.service.dailynews

import com.example.mha.news.model.dailynewsmodel.Base
import retrofit2.Call
import retrofit2.http.*

interface DailynewsInterface {
    @GET
    fun getDaily(@Url url: String): Call<Base>
}