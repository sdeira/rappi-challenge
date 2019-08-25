package com.example.rappichallenge.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
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

class RestaurantsViewModel @Inject constructor(
    private val app: Application,
    private val appRepositoryImplementation: AppRepositoryImplementation
) : ViewModel() {

    var restaurantsResult: MutableLiveData<List<Restaurant>> = MutableLiveData()
    var restaurantsError: MutableLiveData<Boolean> = MutableLiveData(false)
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    lateinit var apiRestaurantsObserver: DisposableObserver<NearbyRestaurants>
    lateinit var dbRestaurantsObserver: DisposableObserver<List<Restaurant>>

    fun getRestaurants() : MutableLiveData<List<Restaurant>> {
        return restaurantsResult
    }

    fun loadRestaurants(lat: String, long: String) {
        isLoading.postValue(true)

        apiRestaurantsObserver = object : DisposableObserver<NearbyRestaurants>() {
            override fun onComplete() {

            }

            override fun onNext(nearbyRestaurants: NearbyRestaurants) {
                setResults(nearbyRestaurants.nearbyRestaurants)
            }

            override fun onError(e: Throwable) {
                setError()
            }
        }

        dbRestaurantsObserver = object : DisposableObserver<List<Restaurant>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Restaurant>) {
                setResults(t)
            }

            override fun onError(e: Throwable) {
                setError()
            }

        }

        if (isNetworkAvailable()) {
            appRepositoryImplementation.getRestaurantsFromApi(lat, long)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(800, TimeUnit.MILLISECONDS)
                .subscribe(apiRestaurantsObserver)
        } else {
            appRepositoryImplementation.getRestaurantsFromDB()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(800, TimeUnit.MILLISECONDS)
                .subscribe(dbRestaurantsObserver)
        }
    }

    fun setResults(list: List<Restaurant>) {
        isLoading.postValue(false)
        restaurantsError.postValue(false)
        restaurantsResult.postValue(list)
    }

    fun setError() {
        isLoading.postValue(false)
        restaurantsError.postValue(true)
    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun disposeElements(){
        if(!apiRestaurantsObserver.isDisposed) apiRestaurantsObserver.dispose()
    }
}