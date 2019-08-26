package com.example.rappichallenge.repository.remote

import com.example.rappichallenge.models.NearbyRestaurants
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.repository.local.RestaurantsDAO
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class AppRepositoryImplementationTest {
    @Mock
    lateinit var appService: AppService
    @Mock
    lateinit var restaurantsDAO: RestaurantsDAO

    lateinit var repository: AppRepositoryImplementation

    val dummySingle = object : Single<List<Restaurant>>() {
        override fun subscribeActual(observer: SingleObserver<in List<Restaurant>>) {

        }

    }

    val dummyObservable = object : Observable<NearbyRestaurants>() {
        override fun subscribeActual(observer: Observer<in NearbyRestaurants>?) {

        }
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(restaurantsDAO.queryRestaurants()).thenReturn(dummySingle)
        Mockito.`when`(appService.getNearbyRestaurants(any(), any())).thenReturn(dummyObservable)
        repository = AppRepositoryImplementation(appService, restaurantsDAO)
    }

    @Test
    fun testGetRestaurantFromDB() {
        val id = "id"
        repository.getRestaurantFromDB(id)
        verify(restaurantsDAO).queryRestaurant(id)
    }

    @Test
    fun testGetRestaurantsFromDB() {
        repository.getRestaurantsFromDB()
        verify(restaurantsDAO).queryRestaurants()
    }

    @Test
    fun testGetRestaurantsFromApi() {
        val lat = "lat"
        val long = "long"
        repository.getRestaurantsFromApi(lat, long)
        verify(appService).getNearbyRestaurants(lat, long)

    }
}