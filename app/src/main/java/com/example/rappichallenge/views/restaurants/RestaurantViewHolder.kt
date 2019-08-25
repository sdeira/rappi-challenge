package com.example.rappichallenge.views.restaurants

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.bumptech.glide.Glide
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.utils.PalleteRequestListener
import kotlinx.android.synthetic.main.restaurant_item_view.view.*

class RestaurantViewHolder(itemView: View) : BaseRestaurantViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            //TODO : Go to detail activity
        }
    }

    override fun bind(restaurant: Restaurant) {
        itemView.restaurant_item_view_name.text = restaurant.name
        itemView.restaurant_item_view_cuisine.text = restaurant.cuisines
        itemView.restaurant_item_view_average_cost.text = "Costo Promedio para dos personas:" + restaurant.averageCostForTwo
        Glide.with(itemView)
            .load(restaurant.thumb)
            .placeholder(ColorDrawable(Color.BLACK))
            .listener(PalleteRequestListener(itemView.restaurant_item_view_image, PorterDuff.Mode.OVERLAY))
            .into(itemView.restaurant_item_view_image)
    }

}