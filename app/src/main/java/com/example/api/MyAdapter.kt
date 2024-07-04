package com.example.api

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(private val context: Activity, private val productArrayList: List<Cart>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val productImage: ShapeableImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.eachitem_layout,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
//        var count = 0
//        for (i in productArrayList.indices){
//            for (j in productArrayList[i].products.indices){
//                count++
//            }
//        }
        return productArrayList.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position].products[0]
        Picasso.get().load(currentItem.thumbnail).into(holder.productImage)
        holder.productName.text = currentItem.title
        holder.productPrice.text = currentItem.price.toString()

    }

}