package com.example.rappichallenge.views.restaurants

import android.widget.Filter
import com.example.rappichallenge.models.Restaurant

class RestaurantsFilter(private val restaurants: List<Restaurant>, private val adapter: RestaurantsAdapter)
    : Filter() {
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val results = FilterResults()

        if (constraint.isNullOrEmpty()) {
            results.count = restaurants.size
            results.values = restaurants
        } else {
            val filteredRestaurants = mutableListOf<Restaurant>()

            restaurants.forEach {
                if (it.name?.contains(constraint, true)!!)
                    filteredRestaurants.add(it)
            }
            results.count = filteredRestaurants.size
            results.values = filteredRestaurants
        }

        return results
    }

    override fun publishResults(p0: CharSequence?, results: FilterResults?) {
        adapter.setFilteredRestaurant(results?.values as List<Restaurant>)
        adapter.notifyDataSetChanged()
    }

}