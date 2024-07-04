package com.example.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("carts")
    fun getProductData() : Call<MyData>
}