package com.example.mha.news.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mha.news.R
import com.example.mha.news.model.dailynewsmodel.Articles
import com.squareup.picasso.Picasso


@Suppress("UNREACHABLE_CODE")
class DailynewsAdapter(private val context: Context, val dailynewsList: ArrayList<Articles>) :
    RecyclerView.Adapter<DailynewsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivDailynews: ImageView = view.findViewById(R.id.ivDailynews)
        val tvDailynewsTitle: TextView = view.findViewById(R.id.tvDailynewsTitle)
        val tvDailynewsDescription: TextView = view.findViewById(R.id.tvDailynewsDescription)
        val tvDailynewsUrl: TextView = view.findViewById(R.id.tvDailynewsUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_dailynews, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get()
            .load(dailynewsList[position].urlToImage)
            .resize(300, 300)
            .into(holder.ivDailynews)
        holder.tvDailynewsTitle.text = "\t" + dailynewsList[position].title
        holder.tvDailynewsDescription.text = "\t" + dailynewsList[position].description
        holder.tvDailynewsUrl.text = dailynewsList[position].url
        holder.tvDailynewsUrl.setOnClickListener {
            val uri: Uri? = Uri.parse(dailynewsList[position].url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return dailynewsList.size
    }
}