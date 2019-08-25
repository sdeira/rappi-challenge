package com.example.rappichallenge.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rappichallenge.models.NearbyRestaurants
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.repository.remote.AppRepositoryImplementation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RestaurantsViewModel @Inject constructor(private val appRepositoryImplementation: AppRepositoryImplementation)
    : ViewModel() {

    var restaurantsResult: MutableLiveData<List<Restaurant>> = MutableLiveData()
    var restaurantsError: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<NearbyRestaurants>

    fun getRestaurants() : MutableLiveData<List<Restaurant>> {
        return restaurantsResult
    }

    fun loadRestaurants(lat: String, long: String) {
        disposableObserver = object : DisposableObserver<NearbyRestaurants>() {
            override fun onComplete() {

            }

            override fun onNext(nearbyRestaurants: NearbyRestaurants) {
                restaurantsResult.postValue(nearbyRestaurants.nearbyRestaurants)
            }

            override fun onError(e: Throwable) {
                restaurantsError.postValue(true)
            }
        }

        appRepositoryImplementation.getRestaurantsFromApi(lat, long)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(800, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)
    }

    fun disposeElements(){
        if(!disposableObserver.isDisposed) disposableObserver.dispose()
    }
}