package com.example.mha.news.weather_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weather::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        private var instance: WeatherDatabase? = null
        fun getWeatherDatabase(context: Context): WeatherDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context, WeatherDatabase::class.java, "Weather")
                    .allowMainThreadQueries().build()
            }
            return instance
        }
    }
}