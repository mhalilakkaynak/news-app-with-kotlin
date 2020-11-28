package com.example.mha.news.weather_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface WeatherDao {
    @Insert
    fun insert(weather: Weather)

    @Delete
    fun delete(weather: Weather)

    @androidx.room.Query("SELECT * FROM Weather")
    fun getAllWeather(): List<Weather>


}