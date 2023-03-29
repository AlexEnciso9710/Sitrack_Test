package com.example.sample_mobiletest.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.sample_mobiletest.R
import com.example.sample_mobiletest.ui.model.InfoUser
import com.example.sample_mobiletest.utils.MapsViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mGoogleMap: GoogleMap//Se declara la variable del mapa como tipo de dato GoogleMap

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout//Se declara variable del swiperefresh como SwipeRefreshLayout

    private lateinit var nameTextView: TextView//Se declaran las variables que se van a utilizar para mostrar en el activity.
    private lateinit var lastnameTextView: TextView
    private lateinit var streetTextView: TextView
    private lateinit var numberTextView: TextView
    private lateinit var countryTextView: TextView
    private lateinit var stateTextView: TextView
    private lateinit var zipcodeTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var imageshow: ImageView

    private lateinit var mapsViewModel: MapsViewModel//Se declara la variable para el ViewModel de maps

    override fun onCreate(savedInstanceState: Bundle?) {//En onCreate se declara el ViewModelProvider.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapsViewModel = ViewModelProvider(this, MapsViewModelFactory())[MapsViewModel::class.java]

        bindView()
        initMapFragment()
        initRefreshLayout()
        initObservers()

        getUser()

    }

    private fun bindView() {//Con bindView se 
        nameTextView = findViewById(R.id.name)
        lastnameTextView = findViewById(R.id.LastName)
        streetTextView = findViewById(R.id.Street)
        numberTextView = findViewById(R.id.ETNumber)
        countryTextView = findViewById(R.id.ETCountry)
        stateTextView = findViewById(R.id.ETState)
        zipcodeTextView = findViewById(R.id.ETZipCode)
        ageTextView = findViewById(R.id.ETAge)
        genderTextView = findViewById(R.id.ETGender)
        emailTextView = findViewById(R.id.ETemail)
        phoneTextView = findViewById(R.id.ETPhone)
        imageshow = findViewById(R.id.imageMainView)

        swipeRefreshLayout = findViewById(R.id.swiperefresh)
    }

    private fun initMapFragment() {//Asignación del mapa para mostrarlo en la Interfaz.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.Map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun initRefreshLayout() = swipeRefreshLayout.run {//Creación del swipeRefreshLayout.
        setOnRefreshListener {
            getUser()
        }
    }

    private fun initObservers() {
        mapsViewModel.mapsUiModel.observe(this) {
            swipeRefreshLayout.isRefreshing = it.showRefresh
            if (it.infoUser != null) getInfoUserSuccess(it.infoUser)
            if (it.exception != null) getInfoUserError(it.exception)
        }
    }

    private fun getUser() = mapsViewModel.getUser()//Se llama a la función getuser que está en mapsViewModel.

    private fun getInfoUserSuccess(infoUser: InfoUser) = infoUser.run {//Función para que cada dato se asigne al mostrador en la interfaz.
        nameTextView.text = name
        lastnameTextView.text = lastname
        ageTextView.text = age
        streetTextView.text = street
        numberTextView.text = number
        countryTextView.text = country
        stateTextView.text = state
        zipcodeTextView.text = zipcode
        genderTextView.text = gender
        emailTextView.text = email
        phoneTextView.text = phone

        Glide.with(this@MapsActivity).load(image).into(imageshow)

        updateMarker(latitude, longitude, city)
    }

    private fun updateMarker(latitude: Double, longitude: Double, city: String) = mGoogleMap.run {//Función para crear el marcador.
        clear()
        val position = LatLng(latitude, longitude)
        addMarker(MarkerOptions()//Añadir marcador con la posición y el nombre de la ciudad.
                        .position(position)
                        .title(city))
        moveCamera(CameraUpdateFactory.newLatLng(position))//Mover la cámara del mapa hacia el marcador.
    }


    private fun getInfoUserError(exception: Exception) = Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()//Lo que se mostrará como texto al fallar la llamada

    override fun onMapReady(googleMap: GoogleMap) {//Creación y asignación del mapa a su variable.
        mGoogleMap = googleMap
    }
}
