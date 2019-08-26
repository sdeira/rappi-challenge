package com.example.rappichallenge.views.restaurants

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.example.rappichallenge.models.Restaurant
import com.nhaarman.mockitokotlin2.validateMockitoUsage
import org.codehaus.plexus.util.ReflectionUtils
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(LayoutInflater::class)
class RestaurantsAdapterTest {

    @Mock
    lateinit var restaurants: LiveData<List<Restaurant>>
    @Mock
    lateinit var layoutInflater: LayoutInflater

    lateinit var adapter: RestaurantsAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        PowerMockito.mockStatic(LayoutInflater::class.java)
        `when`(layoutInflater.inflate(anyInt(), any(ViewGroup::class.java), anyBoolean())).thenReturn(mock(
            View::class.java))
        `when`(LayoutInflater.from(any(Context::class.java))).thenReturn(layoutInflater)

        `when`(restaurants.value).thenReturn(mock(List::class.java) as List<Restaurant>)
        adapter = RestaurantsAdapter(restaurants)
    }

    @After
    fun tearDown() {
        validateMockitoUsage()
    }

    @Test
    fun onCreateViewHolder() {
        val parent = mock(ViewGroup::class.java)
        `when`(parent.context).thenReturn(mock(Context::class.java))

        val viewHolder = adapter.onCreateViewHolder(parent, 0)

        Assert.assertTrue(viewHolder is RestaurantViewHolder)
    }

    @Test
    fun getItemCount() {
        val list = listOf(mock(Restaurant::class.java), mock(Restaurant::class.java))
        `when`(restaurants.value).thenReturn(list)

        val itemCount = adapter.itemCount

        Assert.assertEquals(list.size, itemCount)
    }

    @Test
    fun getItemCount_withFilteredRestaurants() {
        val list = listOf(mock(Restaurant::class.java), mock(Restaurant::class.java))
        adapter.setFilteredRestaurant(list)

        val itemCount = adapter.itemCount

        Assert.assertEquals(list.size, itemCount)
    }

    @Test
    fun onBindViewHolder() {
        val position = 0
        val list = listOf(mock(Restaurant::class.java), mock(Restaurant::class.java))
        val viewHolder = mock(BaseRestaurantViewHolder::class.java)
        `when`(restaurants.value).thenReturn(list)

        adapter.onBindViewHolder(viewHolder, position)

        verify(viewHolder).bind(list[position])
    }

    @Test
    fun onBindViewHolder_withFilteredRestaurants() {
        val position = 0
        val viewHolder = mock(BaseRestaurantViewHolder::class.java)
        val list = listOf(mock(Restaurant::class.java), mock(Restaurant::class.java))
        adapter.setFilteredRestaurant(list)

        adapter.onBindViewHolder(viewHolder, position)

        verify(viewHolder).bind(list[position])
    }

    @Test
    fun getItemViewType() {
        val position = 0
        Assert.assertEquals(0, adapter.getItemViewType(position))
    }

    @Test
    fun setFilteredRestaurants() {
        val list = listOf(mock(Restaurant::class.java), mock(Restaurant::class.java))

        adapter.setFilteredRestaurant(list)

        val filteredRestaurants = ReflectionUtils.getValueIncludingSuperclasses("filteredRestaurants", adapter) as List<Restaurant>
        Assert.assertEquals(list, filteredRestaurants)
    }

    @Test
    fun getFilter() {
        Assert.assertTrue(adapter.filter is RestaurantsFilter)
    }
}