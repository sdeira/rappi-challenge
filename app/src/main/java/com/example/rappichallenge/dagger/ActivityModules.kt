package com.example.rappichallenge.dagger

import com.example.rappichallenge.views.restaurant_detail.RestaurantDetailActivity
import com.example.rappichallenge.views.restaurants.RestaurantsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModules {
    @ContributesAndroidInjector
    internal abstract fun contributesRestaurantsActivity(): RestaurantsActivity

    @ContributesAndroidInjector
    internal abstract fun contributesRestaurantDetailActivity(): RestaurantDetailActivity
}