package com.example.mha.news.view.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mha.news.R
import com.example.mha.news.adapter.CurrencyAdapter
import com.example.mha.news.view.viewmodel.CurrencyViewModel
import kotlinx.android.synthetic.main.currency_fragment.*
import java.lang.Exception

@Suppress("DEPRECATION")
class CurrencyFragment(var position: Int) : Fragment() {
    /*
    position==1->Dollar
    position==2->Euro
    position==3->Sterling
     */
    companion object {
        fun newInstance() = CurrencyFragment(0)
    }

    private lateinit var viewModel: CurrencyViewModel
    private lateinit var adapter: CurrencyAdapter
    private var currency = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.currency_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        getCurrencyData()
        swiperefreshCurrency.setOnRefreshListener {
            getCurrencyData()
            swiperefreshCurrency.isRefreshing = false
        }
    }

    private fun currencyStatus() {
        try {
            currency = when (position) {
                1 -> {
                    ivCurrencyIcon.setImageResource(R.mipmap.dollar_foreground)
                    1.0
                }
                2 -> {
                    ivCurrencyIcon.setImageResource(R.mipmap.euro_foreground)
                    viewModel.currencyModel!!.quotes.uSDEUR
                }
                else -> {
                    ivCurrencyIcon.setImageResource(R.mipmap.sterling_foreground)
                    viewModel.currencyModel!!.quotes.uSDGBP
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getCurrencyData() {
        viewModel.getData()
        Handler().postDelayed({
            currencyStatus()
            currencyShow()
        }, 1000)
    }

    private fun currencyShow() {
        if (!viewModel.currencyErrorMessage) {
            currencyError.visibility = View.INVISIBLE
            recyclerviewCurrency.visibility = View.VISIBLE
            ivCurrencyIcon.visibility = View.VISIBLE
            recyclerviewCurrency.layoutManager = LinearLayoutManager(context)
            recyclerviewCurrency.setHasFixedSize(true)
            adapter = CurrencyAdapter(viewModel.currencyList, currency)
            recyclerviewCurrency.adapter = adapter
        } else {
            currencyError.visibility = View.VISIBLE
            recyclerviewCurrency.visibility = View.INVISIBLE
            ivCurrencyIcon.visibility = View.INVISIBLE
            currencyError.playAnimation()
        }
    }
}