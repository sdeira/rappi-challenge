package com.example.rappichallenge.views.restaurant_detail

import android.widget.ImageView
import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.views.restaurants.RestaurantsActivity
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.restaurant_detail_activity.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RestaurantDetailActivityTest {

    lateinit var activity: RestaurantDetailActivity

    @Mock
    lateinit var name: TextView
    @Mock
    lateinit var cuisine: TextView
    @Mock
    lateinit var average: TextView
    @Mock
    lateinit var hasDelivery: TextView
    @Mock
    lateinit var image: ImageView

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = Mockito.spy(Robolectric.buildActivity(RestaurantDetailActivity::class.java).get())
        Mockito.`when`(activity.restaurant_detail_activity_name).thenReturn(name)
        Mockito.`when`(activity.restaurant_detail_activity_cuisine).thenReturn(cuisine)
        Mockito.`when`(activity.restaurant_detail_activity_average).thenReturn(average)
        Mockito.`when`(activity.restaurant_detail_activity_has_delivery).thenReturn(hasDelivery)
        Mockito.`when`(activity.restaurant_detail_activity_image).thenReturn(image)
    }

    @After
    fun tearDown() {
        Mockito.validateMockitoUsage()
    }

    @Test
    fun testSettingUpView() {
        val id = "id"
        val name = "name"
        val url = "url"
        val average = "200"
        val currency = "$"
        val thumb = "http://image"
        val hasOnlineDelivery = "0"
        val cuisine = "Pizza"
        val featuredImage = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        doNothing().`when`(activity).loadImage(featuredImage)

        activity.setupView(Restaurant(id, name, url, average, currency, thumb, featuredImage,
            null, hasOnlineDelivery, null, cuisine, null))

        verify(this.name).text = name
        verify(this.cuisine).text = cuisine
        verify(this.average).text = "El precio promedio para dos es de:" + average + "$"
        verify(this.hasDelivery).text = "Tiene delivery:NO"
    }
}