package com.example.api

data class MyData(
    val carts: List<Cart>,
    val limit: Int,
    val skip: Int,
    val total: Int
)