package com.example.mha.news.service.currency

import com.example.mha.news.model.currencymodel.Base
import retrofit2.Call
import retrofit2.http.*

interface CurrencyInterface {
    @GET
    fun getCurrency(@Url url: String): Call<Base>
}