package com.example.rappichallenge.viewmodels

import android.app.Application
import androidx.lifecycle.Observer
import com.example.rappichallenge.RxJavaUnitTest
import com.example.rappichallenge.models.NearbyRestaurants
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.repository.remote.AppRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.validateMockitoUsage
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class RestaurantViewModelTest : RxJavaUnitTest() {
    @Mock
    lateinit var repository: AppRepository
    @Mock
    lateinit var app: Application

    lateinit var viewModel: RestaurantsViewModel

    val dummyObservableNearby = object : Observable<NearbyRestaurants>() {
        override fun subscribeActual(observer: io.reactivex.Observer<in NearbyRestaurants>?) {

        }
    }

    val dummyObservable = object : Observable<List<Restaurant>>() {
        override fun subscribeActual(observer: io.reactivex.Observer<in List<Restaurant>>?) {

        }
    }

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        viewModel = spy(RestaurantsViewModel(app, repository))

    }

    @After
    fun tearDown() {
        validateMockitoUsage()
    }

    @Test
    fun getRestaurantFromApi() {
        val lat = "lat"
        val long = "long"
        doReturn(true).`when`(viewModel).isNetworkAvailable()
        Mockito.`when`(repository.getRestaurantsFromApi(lat, long)).thenReturn(dummyObservableNearby)
        viewModel.loadRestaurants(lat, long)
        verify(repository).getRestaurantsFromApi(lat, long)
    }

    @Test
    fun getRestaurantFromDb() {
        val lat = "lat"
        val long = "long"
        doReturn(false).`when`(viewModel).isNetworkAvailable()
        Mockito.`when`(repository.getRestaurantsFromDB()).thenReturn(dummyObservable)
        viewModel.loadRestaurants(lat, long)
        verify(repository).getRestaurantsFromDB()
    }
}