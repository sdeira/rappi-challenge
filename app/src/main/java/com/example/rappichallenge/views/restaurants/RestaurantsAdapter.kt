package com.example.rappichallenge.views.restaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.rappichallenge.R
import com.example.rappichallenge.models.Restaurant

class RestaurantsAdapter(private val restaurants: LiveData<List<Restaurant>>)
    : RecyclerView.Adapter<BaseRestaurantViewHolder>(), Filterable {

    private var filteredRestaurants: List<Restaurant>? = null

    override fun getFilter(): Filter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.restaurant_item_view, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return restaurants.value?.size ?: 0
    }

    fun setFilteredRestaurant(restaurants: List<Restaurant>) {
        filteredRestaurants = restaurants
    }

    override fun onBindViewHolder(holder: BaseRestaurantViewHolder, position: Int) {
        if (filteredRestaurants == null) {
            holder.bind(restaurants.value!![position])
        } else {
            holder.bind(filteredRestaurants!![position])
        }
    }

}