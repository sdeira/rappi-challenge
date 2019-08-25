package com.example.rappichallenge.views.restaurants

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rappichallenge.R
import com.example.rappichallenge.viewmodels.RestaurantsViewModel
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
        viewModel.getRestaurants().observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
        restaurants_list.layoutManager = layoutManager
        restaurants_list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadRestaurants("37.1929572", "-123.8008197,6z")
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
