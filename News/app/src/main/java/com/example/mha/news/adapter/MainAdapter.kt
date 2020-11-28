package com.example.mha.news.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mha.news.R
import com.example.mha.news.adapterlist.weather_adapterlist.ToolsImages
import com.example.mha.news.view.ui.NewsActivity

class MainAdapter(
    private val context: Context,
    private val toolsImagesList: ArrayList<ToolsImages>,
) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val constraintLayoutToolCard: ConstraintLayout =
            view.findViewById(R.id.costraintLayoutToolCard)
        val imageViewTool: ImageView = view.findViewById(R.id.imageViewTool)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.card_tool, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        toolsImagesList[position].imagieView?.let { holder.imageViewTool.setImageResource(it) }
        holder.imageViewTool.setOnClickListener {
            val intent = Intent(context, NewsActivity::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return toolsImagesList.size
    }
}