package com.example.rappichallenge.views.restaurant_detail

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.rappichallenge.R
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.utils.PalleteRequestListener
import com.example.rappichallenge.viewmodels.RestaurantDetailViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.restaurant_detail_activity.*
import javax.inject.Inject

class RestaurantDetailActivity : AppCompatActivity() {

    companion object {
        const val RESTAURANT_EXTRA = "restaurant_extra"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RestaurantDetailViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[RestaurantDetailViewModel::class.java]
    }

    private lateinit var restaurantId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_detail_activity)
        AndroidInjection.inject(this)
        setSupportActionBar(restaurant_detail_activity_toolbar)
        restaurantId = intent.getStringExtra(RESTAURANT_EXTRA)

        viewModel.getRestaurant(restaurantId)
        viewModel.restaurantLiveData.observe(this, Observer {
            setupView(it)
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setupView(restaurant: Restaurant) {
        loadImage(restaurant.featuredImage)
        restaurant_detail_activity_name.text = restaurant.name
        restaurant_detail_activity_cuisine.text = restaurant.cuisines
        restaurant_detail_activity_average.text = getString(R.string.average_price) +
                restaurant.averageCostForTwo + restaurant.currency
        restaurant_detail_activity_has_delivery.text = getString(R.string.has_delivery) +
                if (restaurant.hasOnlineDelivery?.equals("0")!!) "NO" else "SI"
    }

    fun loadImage(imageUri: String?) {
        Glide.with(this)
            .load(imageUri)
            .placeholder(R.drawable.ic_placeholder)
            .listener(PalleteRequestListener(restaurant_detail_activity_image, PorterDuff.Mode.OVERLAY))
            .into(restaurant_detail_activity_image)
    }
}