package com.example.mha.news.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mha.news.R

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        fragmentHolder()
    }

    private fun fragmentHolder() {
        when (intent.extras?.get("position") as Int) {
            0 -> {
                changeFragment(WeatherFragment())
            }
            1 -> {
                changeFragment(CurrencyFragment(1))
            }
            2 -> {
                changeFragment(CurrencyFragment(2))
            }
            3 -> {
                changeFragment(CurrencyFragment(3))
            }
            4 -> {
                changeFragment(DailyNewsFragment())
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayoutNews, fragment)
        fragmentTransaction.commit()
    }
}