package com.example.mha.news.service.weather

import com.example.mha.news.model.weathermodel.WeatherModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*
import java.net.URL

interface WeatherInterface {
    @GET
    fun getWeather(@Url url: String): Call<WeatherModel>

}