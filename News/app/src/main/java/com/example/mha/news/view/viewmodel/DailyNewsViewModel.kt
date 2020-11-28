package com.example.mha.news.view.viewmodel


import androidx.lifecycle.ViewModel
import com.example.mha.news.model.dailynewsmodel.Articles
import com.example.mha.news.model.dailynewsmodel.Base
import com.example.mha.news.service.dailynews.DailynewsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyNewsViewModel : ViewModel() {
    var dailynewsError: Boolean = true
    var dailynewsModel: Base? = null
    val dailynewsList = ArrayList<Articles>()
    fun getData(regionName: String) {
        DailynewsApiService.api.getDaily("v2/top-headlines?country=$regionName&apiKey=033f2f2c5c674a57a2b335003a8ce810")
            .enqueue(object : Callback<Base> {
                override fun onResponse(call: Call<Base>, response: Response<Base>) {
                    dailynewsList.clear()
                    dailynewsModel = response.body()
                    dailynewsList.addAll(dailynewsModel!!.articles)
                    dailynewsError = false
                }

                override fun onFailure(call: Call<Base>, t: Throwable) {
                    dailynewsError = true
                }
            })
    }
}