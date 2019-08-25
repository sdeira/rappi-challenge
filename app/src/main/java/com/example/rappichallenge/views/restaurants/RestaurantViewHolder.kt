package com.example.rappichallenge.views.restaurants

import android.view.View
import com.example.rappichallenge.models.Restaurant
import kotlinx.android.synthetic.main.restaurant_item_view.view.*

class RestaurantViewHolder(itemView: View) : BaseRestaurantViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            //TODO : Go to detail activity
        }
    }

    override fun bind(restaurant: Restaurant) {
        itemView.restaurant_item_view_name.text = restaurant.name
    }

}