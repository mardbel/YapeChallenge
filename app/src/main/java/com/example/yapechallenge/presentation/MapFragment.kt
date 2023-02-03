package com.example.yapechallenge.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.yapechallenge.R
import com.example.yapechallenge.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var map : GoogleMap
    lateinit var binding: FragmentMapBinding
    private val args : MapFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.fragment_child_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val currentLat = args.latitude
        val currentLong = args.longitude

        val currentReceiptOrigin = LatLng(currentLat!!.toDouble(), currentLong!!.toDouble())
        map.addMarker(
            MarkerOptions()
                .position(currentReceiptOrigin))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentReceiptOrigin, 10f), 4000, null)
    }
}