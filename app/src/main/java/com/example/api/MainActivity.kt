package com.example.api

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView: RecyclerView = findViewById(R.id.RecyclerView)
//        val TextviewData: TextView = findViewById(R.id.TextviewData)
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getProductData()

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // If API Call is a success
                val responseBody = response.body()!!
                val productList = responseBody.carts
                val collectDataInStringBuilder = StringBuilder()
                myAdapter = MyAdapter(this@MainActivity, responseBody.carts)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
//                var count = 0
//                for (i in responseBody.carts.indices) {
//                    for (j in responseBody.carts[i].products.indices) {
//                        count++
//                        val productNumberText: String = "$count . "
//                        collectDataInStringBuilder.append(productNumberText)
//                        collectDataInStringBuilder.append(responseBody.carts[i].products[j].title)
//                        collectDataInStringBuilder.append("  ${responseBody.carts[i].products[j].price}")
//                        collectDataInStringBuilder.append("\n")
//
//                    }
//                }
//                TextviewData.text = collectDataInStringBuilder.toString()


//                Toast.makeText(applicationContext, "Total number of products are : $count", Toast.LENGTH_LONG).show()


            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // If API Call fails
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }
}