package com.example.newsapi.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.Model.ArticleList
import com.example.newsapi.databinding.NewsLayoutBinding
import com.squareup.picasso.Picasso

class NewsAdapter(var context: Context, var newsList: MutableList<ArticleList>) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>(){

    class MyViewHolder(var binding: NewsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = NewsLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var news = newsList[position]

        Picasso.get().load(news.image).into(holder.binding.ivNews)

        holder.binding.newsContent.text = news.content
        holder.binding.newsTitle.text = news.title
        holder.binding.cardView.setOnClickListener {
            openUrl(news.url, it.context)
        }
    }

    private fun openUrl(url: String, context: Context?) {
        url.let {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            if (context != null) {
                context.startActivity(intent)
            }
    }
}
    }