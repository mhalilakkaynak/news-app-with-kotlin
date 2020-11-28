package com.example.mha.news.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mha.news.adapterlist.weather_adapterlist.WeatherList
import com.example.mha.news.model.weathermodel.WeatherModel
import com.example.mha.news.service.weather.WeatherApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.properties.Delegates

class WeatherViewModel : ViewModel() {
    var weatherErrorMessage by Delegates.notNull<Boolean>()
    var weatherModelList = ArrayList<WeatherList>()
    private val weatherApiService = WeatherApiService.api

    fun getData(city: ArrayList<String>) {
        for (i in city) {
            weatherApiService.getWeather("weather?q=$i&appid=651ab7a4bb3e894605b684bcac0e2482")
                .enqueue(object : Callback<WeatherModel> {
                    override fun onResponse(
                        call: Call<WeatherModel>,
                        response: Response<WeatherModel>,
                    ) {
                        try {
                            weatherModelList.add(
                                WeatherList(
                                    response.body()!!.main.temp,
                                    response.body()!!.main.feels_like,
                                    response.body()!!.wind.speed,
                                    response.body()!!.main.temp_min,
                                    response.body()!!.main.temp_max,
                                    response.body()!!.weather[0].main,
                                    response.body()!!.name
                                )
                            )
                            weatherErrorMessage = false
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                        weatherErrorMessage = true
                    }
                })
        }
    }
}
