package com.example.mha.news.view.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mha.news.R
import com.example.mha.news.adapter.DailynewsAdapter
import com.example.mha.news.view.viewmodel.DailyNewsViewModel
import kotlinx.android.synthetic.main.daily_news_fragment.*

@Suppress("DEPRECATION")
class DailyNewsFragment : Fragment() {

    companion object {
        fun newInstance() = DailyNewsFragment()
    }

    private lateinit var viewModel: DailyNewsViewModel
    private lateinit var adapter: DailynewsAdapter
    private lateinit var regionName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.daily_news_fragment, container, false)
    }

    @SuppressLint("CommitPrefEdits")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DailyNewsViewModel::class.java)
        regionName = "us"
        getDailynewsData()
        swiperefreshDailynews.setOnRefreshListener {
            getDailynewsData()
            swiperefreshDailynews.isRefreshing = false
        }
        toolbar()
    }

    @SuppressLint("InflateParams", "CommitPrefEdits")
    private fun toolbar() {
        toolbarDailynews.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.itemsettings -> {
                    val alertdialogView =
                        LayoutInflater.from(context).inflate(R.layout.region_alertdialog, null)
                    AlertDialog.Builder(context, R.style.ThemeOverlay_AppCompat_Dialog_Alert)
                        .setTitle("Daily news")
                        .setMessage("Enter region")
                        .setIcon(R.mipmap.dailynews_foreground)
                        .setView(alertdialogView)
                        .setPositiveButton("Ok") { dialog, which ->
                            regionName =
                                alertdialogView.findViewById<EditText>(R.id.edtCityNameEnter).text.toString()
                            getDailynewsData()
                        }.setNegativeButton("Delete") { dialog, which ->
                        }
                        .create().show()
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun getDailynewsData() {
        viewModel.getData(regionName)
        Handler().postDelayed({
            dailynewsShow()
        }, 1000)
    }

    private fun dailynewsShow() {
        if (viewModel.dailynewsError) {
            recyclerviewDailynews.visibility = View.INVISIBLE
            dailynewsError.visibility = View.VISIBLE
            dailynewsError.playAnimation()
        } else {
            recyclerviewDailynews.visibility = View.VISIBLE
            dailynewsError.visibility = View.INVISIBLE
            recyclerviewDailynews.setHasFixedSize(true)
            recyclerviewDailynews.layoutManager = LinearLayoutManager(context)
            adapter = DailynewsAdapter(context!!, viewModel.dailynewsList)
            recyclerviewDailynews.adapter = adapter
        }
    }
}