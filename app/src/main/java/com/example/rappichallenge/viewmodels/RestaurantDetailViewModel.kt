package com.example.rappichallenge.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rappichallenge.models.NearbyRestaurants
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.repository.remote.AppRepository
import com.example.rappichallenge.repository.remote.AppRepositoryImplementation
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RestaurantDetailViewModel @Inject constructor(private val appRepository:
                                                    AppRepository
) : ViewModel() {

    var restaurantLiveData: MutableLiveData<Restaurant> = MutableLiveData()
    var restaurantError: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var restaurantObserver: DisposableObserver<Restaurant>

    fun getRestaurant(id: String) {
        restaurantObserver = object : DisposableObserver<Restaurant>() {
            override fun onComplete() {

            }

            override fun onNext(restaurant: Restaurant) {
                restaurantLiveData.postValue(restaurant)
            }

            override fun onError(e: Throwable) {
                restaurantError.postValue(true)
            }
        }

        appRepository.getRestaurantFromDB(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(800, TimeUnit.MILLISECONDS)
            .subscribe(restaurantObserver)
    }


}