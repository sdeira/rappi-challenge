package com.example.rappichallenge.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.rappichallenge.R
import com.example.rappichallenge.viewmodels.RestaurantsViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class RestaurantsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RestaurantsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[RestaurantsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadRestaurants("-34.454350", "-58.538330")
    }
}
