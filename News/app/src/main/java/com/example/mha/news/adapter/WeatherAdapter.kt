package com.example.mha.news.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mha.news.R
import com.example.mha.news.adapterlist.weather_adapterlist.WeatherList

class WeatherAdapter(
    private val weatherList: ArrayList<WeatherList>,
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTemperature: TextView = view.findViewById(R.id.tvTemperature)
        val tvRealfeel: TextView = view.findViewById(R.id.tvRealfeel)
        val tvMain: TextView = view.findViewById(R.id.tvMain)
        val tvTempMax: TextView = view.findViewById(R.id.tvTempMax)
        val tvWindspeed: TextView = view.findViewById(R.id.tvWindspeed)
        val tvTempmin: TextView = view.findViewById(R.id.tvTempmin)
        val tvCityName: TextView = view.findViewById(R.id.tvCityName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_weather, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvTemperature.text =
            "${(weatherList[position].temperature?.minus(273.15))?.toInt()}°C"
        holder.tvRealfeel.text = "${weatherList[position].feelsLike?.minus(273.15)?.toFloat()} °C"
        holder.tvTempMax.text = "${weatherList[position].tempMax?.minus(273.15)?.toFloat()} °C"
        holder.tvWindspeed.text =
            "${weatherList[position].windSpeed?.times(1.609344)?.toFloat()} km/h"
        holder.tvTempmin.text = "${weatherList[position].tempMin?.minus(273.15)?.toFloat()} °C"
        holder.tvMain.text = weatherList[position].main
        holder.tvCityName.text = weatherList[position].cityName
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}