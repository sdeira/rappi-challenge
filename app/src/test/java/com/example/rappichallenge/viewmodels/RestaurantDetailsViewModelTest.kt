package com.example.rappichallenge.viewmodels

import androidx.annotation.NonNull
import com.example.rappichallenge.RxJavaUnitTest
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.repository.remote.AppRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.validateMockitoUsage
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import org.mockito.Mockito
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class RestaurantDetailsViewModelTest : RxJavaUnitTest() {
    @Mock
    lateinit var repository: AppRepository

    lateinit var viewModel: RestaurantDetailViewModel

    val dummyObservable = object : Observable<Restaurant>() {
        override fun subscribeActual(observer: Observer<in Restaurant>?) {

        }

    }

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(repository.getRestaurantFromDB(any())).thenReturn(dummyObservable)
        viewModel = RestaurantDetailViewModel(repository)

    }

    @After
    fun tearDown() {
        validateMockitoUsage()
    }

    @Test
    fun getRestaurant() {
        val id = "id"
        viewModel.getRestaurant(id)
        verify(repository).getRestaurantFromDB(id)
    }
}