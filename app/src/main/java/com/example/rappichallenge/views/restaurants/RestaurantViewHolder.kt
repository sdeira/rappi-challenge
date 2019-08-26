package com.example.rappichallenge.views.restaurants

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import com.bumptech.glide.Glide
import com.example.rappichallenge.R
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.utils.PalleteRequestListener
import com.example.rappichallenge.views.restaurant_detail.RestaurantDetailActivity
import com.example.rappichallenge.views.restaurant_detail.RestaurantDetailActivity.Companion.RESTAURANT_EXTRA
import kotlinx.android.synthetic.main.restaurant_item_view.view.*

class RestaurantViewHolder(itemView: View) : BaseRestaurantViewHolder(itemView) {

    private lateinit var restaurant: Restaurant

    init {
        itemView.setOnClickListener {
            val detailIntent = Intent(it.context, RestaurantDetailActivity::class.java)
            detailIntent.putExtra(RESTAURANT_EXTRA, restaurant.id)
            it.context.startActivity(detailIntent)
        }
    }

    override fun bind(restaurant: Restaurant) {
        this.restaurant = restaurant
        itemView.restaurant_item_view_name.text = restaurant.name
        itemView.restaurant_item_view_cuisine.text = restaurant.cuisines
        itemView.restaurant_item_view_average_cost.text = itemView.context.getString(R.string.average_price) +
                restaurant.averageCostForTwo + restaurant.currency
        itemView.restaurant_item_view_rate.text = restaurant.userRating?.aggregateRating
        setTextViewDrawableColor(itemView.restaurant_item_view_rate, Color.parseColor("#"
                + restaurant.userRating?.ratingColor))

        Glide.with(itemView)
            .load(restaurant.thumb)
            .placeholder(ColorDrawable(Color.BLACK))
            .listener(PalleteRequestListener(itemView.restaurant_item_view_image, PorterDuff.Mode.OVERLAY))
            .into(itemView.restaurant_item_view_image)
    }

    private fun setTextViewDrawableColor(textView: TextView, color: Int?) {
        color?.let {
            textView.setTextColor(color)
            for (drawable: Drawable in textView.compoundDrawables) {
                drawable?.colorFilter = PorterDuffColorFilter(color,
                    PorterDuff.Mode.SRC_IN)
            }
        }

    }

}