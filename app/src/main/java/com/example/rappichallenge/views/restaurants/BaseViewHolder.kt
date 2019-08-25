package com.example.rappichallenge.views.restaurants

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rappichallenge.models.Restaurant

abstract class BaseRestaurantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(restaurant: Restaurant)
}