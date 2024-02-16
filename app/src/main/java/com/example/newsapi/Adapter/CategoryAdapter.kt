package com.example.newsapi.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.newsapi.Model.Category
import com.example.newsapi.databinding.CategoryLayoutBinding

class CategoryAdapter(var context: Context, var categoryList: MutableList<Category>) :Adapter<CategoryAdapter.MyViewHolder>(){

    var categoryClickListener :((position:Int,category:Category) -> Unit)? = null

    class MyViewHolder(var binding: CategoryLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = CategoryLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var category = categoryList[position]

        holder.binding.tvCategory.text = category.category

        holder.binding.tvCategory.setOnClickListener {
            categoryClickListener?.invoke(position,category)
        }

    }
}