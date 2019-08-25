package com.example.rappichallenge.views

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.rappichallenge.R
import com.example.rappichallenge.views.restaurants.RestaurantsActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_maps.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {
    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
        const val PREFS_NAME = "rappi_prefs"
        const val LAT = "latitude"
        const val LONG = "longitude"
    }

    private lateinit var mMap: GoogleMap
    private lateinit var latlong: LatLng
    private var isLocated = false

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {

    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            if (!isLocated) {
                latlong = LatLng(location.latitude, location.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlong, 16.0f))
                isLocated = true
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val prefs =  getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if(prefs.getString(LAT, "").isNotEmpty() && prefs.getString(LONG, "").isNotEmpty()) {
            goToMainActivity()
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        activity_maps_button.setOnClickListener {
            val center = mMap.cameraPosition.target
            persistLatAndLog(center)
            goToMainActivity()
        }
    }

    fun goToMainActivity() {
        val mainIntent = Intent(this, RestaurantsActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    fun persistLatAndLog(latlong: LatLng) {
        val sharePref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharePref.edit()
            .putString(LAT, latlong.latitude.toString())
            .putString(LONG, latlong.longitude.toString())
            .commit()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMinZoomPreference(1.0f)
        mMap.setMaxZoomPreference(16.0f)
        mMap.setOnCameraMoveListener {  }
        mMap.setOnCameraIdleListener {  }
        getLocation()
    }

    fun getLocation() {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
            return
        }
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_GRANTED -> getLocation()
                PackageManager.PERMISSION_DENIED -> finish()
            }
        }
    }
}
