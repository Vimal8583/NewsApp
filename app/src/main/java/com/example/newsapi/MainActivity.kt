package com.example.newsapi

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapi.Adapter.CategoryAdapter
import com.example.newsapi.Adapter.NewsAdapter
import com.example.newsapi.Model.ArticleList
import com.example.newsapi.Model.Category
import com.example.newsapi.Model.Countries
import com.example.newsapi.Model.Data
import com.example.newsapi.Network.ApiClient
import com.example.newsapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding

    private var countryList = mutableListOf<Countries>()
    private lateinit var countryAdapter: ArrayAdapter<String>

    private var categoryList = mutableListOf<Category>()
    private lateinit var categoryAdapter:CategoryAdapter

    private lateinit var newsAdapter: NewsAdapter
    private var articles = mutableListOf<ArticleList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Country List
        prepareCountryList()
        countryAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,countryList.map { it.countryName })
        binding.spCountry.adapter = countryAdapter

        binding.spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                val selectedCountryCode = countryList[position].countryCode
                // Use the selectedCountryCode as needed (e.g., pass it to a function)
                fetchNews(selectedCountryCode)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing here
            }
        }


        //Category
        categoryData()
        categoryAdapter = CategoryAdapter(this,categoryList)
        binding.listCategory.adapter = categoryAdapter
        binding.listCategory.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        //News
        newsAdapter = NewsAdapter(this, articles)
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.layoutManager = LinearLayoutManager(this)

        fetchNews(countryList.first().countryCode)
    }



    private fun prepareCountryList() {
        countryList.add(Countries(1,"India","in"))
        countryList.add(Countries(2,"Australia","au"))
        countryList.add(Countries(3,"China","cn"))
        countryList.add(Countries(4,"France","fr"))
        countryList.add(Countries(5,"Hong Kong","hk"))
        countryList.add(Countries(6,"Switzerland","ch"))
        countryList.add(Countries(7,"Saudi Arabia","sa"))
        countryList.add(Countries(8,"Philippines","ph"))
        countryList.add(Countries(9,"Russia","ru"))
        countryList.add(Countries(10,"Germany","de"))
    }

    private fun categoryData() {
        categoryList.add(Category(1,"business"))
        categoryList.add(Category(2,"entertainment"))
        categoryList.add(Category(3,"general"))
        categoryList.add(Category(4,"health"))
        categoryList.add(Category(5,"science"))
        categoryList.add(Category(6,"sports"))
        categoryList.add(Category(7,"technology"))
    }

    private fun fetchNews(countryCode:String) {
        ApiClient.init().getNews(countryCode).enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful) {
                    var article = response.body()?.articles
                    article?.let {
                        newsAdapter.newsList = it
                        newsAdapter.notifyDataSetChanged()
                        categoryAdapter.categoryClickListener={
                                position, category ->
                            getCategoryData(countryCode,position, category)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getCategoryData(countryCode: String,position: Int, category: Category) {
        ApiClient.init().getCategoryNews(countryCode,category.category).enqueue(object :Callback<Data>{
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful){
                    var news = response.body()?.articles
                    news?.let {
                        newsAdapter.newsList = it
                        newsAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }

        })
    }
    }