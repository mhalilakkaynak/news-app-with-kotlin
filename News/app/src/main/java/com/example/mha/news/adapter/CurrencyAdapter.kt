package com.example.mha.news.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mha.news.R
import com.example.mha.news.adapterlist.currency_adapterlist.CurrencyList

class CurrencyAdapter(
    private val currencyList: ArrayList<CurrencyList>,
    private val currency: Double,
) :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCardCurrency: TextView = view.findViewById(R.id.tvCardCurrency)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_currency, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCardCurrency.text =
            "${currencyList[position].isoName}:${((currencyList[position].quantity) / currency).toFloat()}"
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }
}