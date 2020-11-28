package com.example.mha.news.adapterlist.weather_adapterlist

data class WeatherList(
    var temperature: Double? = null,
    var feelsLike: Double? = null,
    var windSpeed: Double? = null,
    var tempMin: Double? = null,
    var tempMax: Double? = null,
    var main: String? = null,
    var cityName: String? = null,
) {
}