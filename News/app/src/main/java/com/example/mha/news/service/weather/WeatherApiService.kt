package com.example.mha.news.service.weather

import com.example.mha.news.model.weathermodel.WeatherModel
import com.example.mha.news.service.RetrofitClient
import retrofit2.Call

class WeatherApiService {
    companion object {
        private val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        val api = RetrofitClient.getClient(BASE_URL).create(WeatherInterface::class.java)

    }
}