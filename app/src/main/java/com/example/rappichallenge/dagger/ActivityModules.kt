package com.example.rappichallenge.dagger

import com.example.rappichallenge.views.RestaurantsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModules {
    @ContributesAndroidInjector
    internal abstract fun contributesRestaurantsActivity(): RestaurantsActivity
}