package com.example.rappichallenge.views.restaurants

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import com.example.rappichallenge.R
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.restaurants_activity.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RestaurantsActivityTest {
    @Mock
    lateinit var menuInflater: MenuInflater

    @Mock
    lateinit var recyclerView: RecyclerView

    lateinit var activity: RestaurantsActivity

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = Mockito.spy(Robolectric.buildActivity(RestaurantsActivity::class.java).get())
        Mockito.`when`(activity.restaurants_list).thenReturn(recyclerView)
        Mockito.`when`(activity.menuInflater).thenReturn(menuInflater)
    }

    @After
    fun tearDown() {
        Mockito.validateMockitoUsage()
    }

    @Test
    fun onCreateOptionsMenu() {
        val searchView = Mockito.mock(SearchView::class.java)
        val searchItem = Mockito.mock(MenuItem::class.java)
        val menu = Mockito.mock(Menu::class.java)
        Mockito.`when`(searchItem.actionView).thenReturn(searchView)
        Mockito.`when`(menu.findItem(R.id.action_search)).thenReturn(searchItem)

        activity.onCreateOptionsMenu(menu)

        Mockito.verify(searchView)
            .setOnQueryTextListener(ArgumentMatchers.any(SearchView.OnQueryTextListener::class.java))
    }

}