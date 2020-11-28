package com.example.mha.news.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mha.news.R
import com.example.mha.news.adapter.MainAdapter
import com.example.mha.news.adapterlist.weather_adapterlist.ToolsImages
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView()

    }

    private fun recyclerView() {
        val toolsImagesList = ArrayList<ToolsImages>()
        toolsImagesList.add(ToolsImages(R.mipmap.weather_foreground))
        toolsImagesList.add(ToolsImages(R.mipmap.dollar_foreground))
        toolsImagesList.add(ToolsImages(R.mipmap.euro_foreground))
        toolsImagesList.add(ToolsImages(R.mipmap.sterling_foreground))
        toolsImagesList.add(ToolsImages(R.mipmap.dailynews_foreground))
        val adapter = MainAdapter(this, toolsImagesList)
        recyclerViewMain.setHasFixedSize(true)
        recyclerViewMain.layoutManager = GridLayoutManager(this, 2)
        recyclerViewMain.adapter = adapter
    }
}