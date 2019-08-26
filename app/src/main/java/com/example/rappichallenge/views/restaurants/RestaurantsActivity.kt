package com.example.rappichallenge.views.restaurants

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rappichallenge.R
import com.example.rappichallenge.viewmodels.RestaurantsViewModel
import com.example.rappichallenge.views.MapsActivity.Companion.LAT
import com.example.rappichallenge.views.MapsActivity.Companion.LONG
import com.example.rappichallenge.views.MapsActivity.Companion.PREFS_NAME
import com.google.android.gms.maps.model.LatLng
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.restaurants_activity.*
import javax.inject.Inject


class RestaurantsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RestaurantsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[RestaurantsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurants_activity)
        AndroidInjection.inject(this)
        setSupportActionBar(toolbar)

        val adapter = RestaurantsAdapter(viewModel.getRestaurants())
        val layoutManager = LinearLayoutManager(this)
        restaurants_list.layoutManager = layoutManager
        restaurants_list.adapter = adapter

        viewModel.getRestaurants().observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
        viewModel.restaurantsError.observe(this, Observer {
            error_text.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.isLoading.observe(this, Observer {
            loading_spinner.visibility = if (it) View.VISIBLE else View.GONE
        })

        val latLong = getLatLong()
        viewModel.loadRestaurants(latLong.latitude.toString(), latLong.longitude.toString())

    }

    fun getLatLong() : LatLng {
        val prefs =  getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return LatLng(prefs.getString(LAT, "0").toDouble(), prefs.getString(LONG, "0").toDouble())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                (restaurants_list.adapter as RestaurantsAdapter).filter.filter(newText)
                return false
            }
        })
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeElements()
    }
}
