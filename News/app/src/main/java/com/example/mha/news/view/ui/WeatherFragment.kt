package com.example.mha.news.view.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mha.news.R
import com.example.mha.news.adapter.WeatherAdapter
import com.example.mha.news.view.viewmodel.WeatherViewModel
import com.example.mha.news.weather_database.Weather
import com.example.mha.news.weather_database.WeatherDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.weather_fragment.*
import java.lang.Exception
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class WeatherFragment : Fragment() {
    companion object {
        fun newInstance() = WeatherFragment()
    }

    private lateinit var viewModel: WeatherViewModel
    private lateinit var adapter: WeatherAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getWeatherData()
        addCity()
        deleteCity()
        try {
            swiperefreshWeather.setOnRefreshListener {
                getWeatherData()
                swiperefreshWeather.isRefreshing = false
            }
        } catch (e: Exception) {
            Snackbar.make(swiperefreshWeather, "Error", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun getWeatherData() {
        val cityNameList = ArrayList<String>()
        viewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        val weatherDatabase = WeatherDatabase.getWeatherDatabase(context!!)
        val weatherDataList = weatherDatabase?.weatherDao()?.getAllWeather()
        cityNameList.add("Bursa")
        for (i in weatherDataList!!) {
            cityNameList.add(i.cityName!!)
        }
        viewModel.weatherModelList.clear()
        viewModel.getData(cityNameList)
        Handler().postDelayed({
            weatherShow()
        }, 500)
    }

    private fun weatherShow() {
        if (viewModel.weatherErrorMessage) {
            weatherError.visibility = View.VISIBLE
            ibtnAddCity.visibility = View.INVISIBLE
            ibtnDeleteCity.visibility = View.INVISIBLE
            weatherError.playAnimation()
        } else {
            weatherError.visibility = View.INVISIBLE
            ibtnAddCity.visibility = View.VISIBLE
            ibtnDeleteCity.visibility = View.VISIBLE
            recyclerViewWeather.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            recyclerViewWeather.setHasFixedSize(true)
            adapter = WeatherAdapter(viewModel.weatherModelList)
            recyclerViewWeather.adapter = adapter
        }
    }

    @SuppressLint("InflateParams")
    private fun deleteCity() {
        ibtnDeleteCity.setOnClickListener {
            weatherAlertDialog(false)
        }
    }

    private fun addCity() {
        ibtnAddCity.setOnClickListener {
            val weatherDatabase = WeatherDatabase.getWeatherDatabase(context!!)
            weatherAlertDialog(true)
        }
    }

    private fun weatherAlertDialog(control: Boolean) {
        val view = layoutInflater.inflate(R.layout.region_alertdialog, null)
        AlertDialog.Builder(context!!, R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setTitle("Weather")
            .setMessage("Enter region")
            .setIcon(R.mipmap.weather_foreground)
            .setView(view)
            .setPositiveButton("Ok") { dialog, which ->
                if (control) {
                    try {
                        dataInsertCity(view.findViewById<EditText>(R.id.edtCityNameEnter).text.toString())
                        Snackbar.make(ibtnAddCity, "City saved", Snackbar.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Snackbar.make(ibtnAddCity, "ERROR", Snackbar.LENGTH_SHORT).show()
                    }
                } else {
                    try {
                        dataDeleteCity(view.findViewById<EditText>(R.id.edtCityNameEnter).text.toString())
                        Snackbar.make(ibtnAddCity, "City deleted", Snackbar.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Snackbar.make(ibtnAddCity, "ERROR", Snackbar.LENGTH_SHORT).show()
                    }
                }
                getWeatherData()
            }
            .setNegativeButton("Delete") { dialog, which ->
                if (control) {
                    Snackbar.make(ibtnAddCity, "City not saved", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(ibtnAddCity, "City not deleted", Snackbar.LENGTH_SHORT).show()
                }
            }
            .create().show()
    }

    private fun dataDeleteCity(cityName: String) {
        val weatherDatabase = WeatherDatabase.getWeatherDatabase(context!!)
        val weatherDataList = weatherDatabase?.weatherDao()?.getAllWeather()
        for (i in weatherDataList!!) {
            if (i.cityName == cityName) {
                weatherDatabase.weatherDao().delete(Weather(i.cityName, i.weatherId))
                break
            }
        }
    }

    private fun dataInsertCity(cityName: String) {
        val weather = Weather(cityName)
        val weatherDatabase = WeatherDatabase.getWeatherDatabase(context!!)
        weatherDatabase?.weatherDao()?.insert(weather)
    }
}