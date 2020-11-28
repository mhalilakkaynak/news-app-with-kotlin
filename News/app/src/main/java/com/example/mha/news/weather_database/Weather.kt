package com.example.mha.news.weather_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weather")
data class Weather(
    @ColumnInfo(name = "weatherCityName")
    var cityName: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "weatherId")
    var weatherId: Int? = null,
    )
