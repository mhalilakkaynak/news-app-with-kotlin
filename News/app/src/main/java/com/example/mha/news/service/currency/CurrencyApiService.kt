package com.example.mha.news.service.currency

import com.example.mha.news.model.currencymodel.Base
import com.example.mha.news.service.RetrofitClient
import retrofit2.Call

class CurrencyApiService {
    companion object {
        private val BASE_URL = "http://api.currencylayer.com/"
        private val api = RetrofitClient.getClient(BASE_URL).create(CurrencyInterface::class.java)
        fun getData(): Call<Base> {
            return api.getCurrency("live?access_key=3346e4486fa012473f073e089673fb18&format=1")
        }
    }

}