package bry1337.github.io.deliveryman.presentation.location

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import bry1337.github.io.deliveryman.MainActivity
import bry1337.github.io.deliveryman.R
import bry1337.github.io.deliveryman.databinding.FragmentMapLocationBinding
import bry1337.github.io.deliveryman.injection.ViewModelFactory
import bry1337.github.io.deliveryman.injection.module.GlideApp
import bry1337.github.io.deliveryman.model.Delivery
import bry1337.github.io.deliveryman.model.Location
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map_location.*

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
class LocationFragment : Fragment(), OnMapReadyCallback {

  private lateinit var viewModel: LocationViewModel
  private lateinit var activity: MainActivity
  private lateinit var binding: FragmentMapLocationBinding

  private lateinit var map: GoogleMap
  private lateinit var delivery: Delivery

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentMapLocationBinding.inflate(inflater, container, false)
    val mapFragment = (childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?)
    mapFragment?.getMapAsync(this)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    delivery = LocationFragmentArgs.fromBundle(arguments!!).delivery
    viewModel = ViewModelProviders.of(this, ViewModelFactory(activity)).get(LocationViewModel::class.java)
    viewModel.setDelivery(delivery)
    viewModel.description.observe(this, Observer { description ->
      tvDescription.text = description
    })
    viewModel.imageUrl.observe(this, Observer { imageUrl ->
      GlideApp.with(this).load(imageUrl).centerCrop().into(ivImage)
    })
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    activity = context as MainActivity
  }

  override fun onMapReady(googleMap: GoogleMap?) {
    this.map = googleMap!!
    val location = LatLng(delivery.location.lat!!, delivery.location.lng!!)

    map.addMarker(MarkerOptions().position(location).title(getString(R.string.go_to_here)))
    map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17f))
    if (location != null) {
      updateMap()
    }
  }

  private fun updateMap() {
    val addressLocation = LatLng(delivery.location.lat!!, delivery.location.lng!!)
    map.addMarker(MarkerOptions().position(addressLocation).title(getString(R.string.go_to_here)))
    map.moveCamera(CameraUpdateFactory.newLatLng(addressLocation))
  }
}