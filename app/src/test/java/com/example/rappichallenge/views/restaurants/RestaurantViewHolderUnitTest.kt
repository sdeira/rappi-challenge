package com.example.rappichallenge.views.restaurants

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import com.example.rappichallenge.models.Restaurant
import com.example.rappichallenge.models.UserRating
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.restaurant_detail_activity.*
import kotlinx.android.synthetic.main.restaurant_item_view.view.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class RestaurantViewHolderUnitTest {

    lateinit var viewHolder: RestaurantViewHolder

    @Mock
    lateinit var name: TextView
    @Mock
    lateinit var cuisine: TextView
    @Mock
    lateinit var average: TextView
    @Mock
    lateinit var rate: TextView
    @Mock
    lateinit var itemView: View

    @Mock
    lateinit var image: ImageView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(itemView.restaurant_item_view_name).thenReturn(name)
        Mockito.`when`(itemView.restaurant_item_view_cuisine).thenReturn(cuisine)
        Mockito.`when`(itemView.restaurant_item_view_average_cost).thenReturn(average)
        Mockito.`when`(itemView.restaurant_item_view_rate).thenReturn(rate)
        Mockito.`when`(itemView.restaurant_item_view_image).thenReturn(image)
        Mockito.`when`(itemView.context).thenReturn(ApplicationProvider.getApplicationContext())
        viewHolder = spy(RestaurantViewHolder(itemView))
    }

    @Test
    fun testBind() {
        val id = "id"
        val name = "name"
        val url = "url"
        val average = "200"
        val currency = "$"
        val thumb = "http://image"
        val hasOnlineDelivery = "0"
        val cuisine = "Pizza"
        val featuredImage = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"
        val agregateRating = "4"
        val userRating = UserRating(agregateRating, "DA45c3")
        Mockito.`when`(this.rate.compoundDrawables).thenReturn(arrayOf<Drawable>())
        doNothing().`when`(viewHolder).loadImage()
        viewHolder.bind(
            Restaurant(id, name, url, average, currency, thumb, featuredImage,
                userRating, hasOnlineDelivery, null, cuisine, null)
        )

        verify(this.name).text = name
        verify(this.cuisine).text = cuisine
        verify(this.average).text = "El precio promedio para dos es de:" + average + "$"
        verify(this.rate).text = agregateRating

    }
}