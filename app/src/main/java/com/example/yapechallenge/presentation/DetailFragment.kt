package com.example.yapechallenge.presentation

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yapechallenge.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photo = args.photo
        Glide.with(binding.imgDetail.context).load(photo).into(binding.imgDetail)
        binding.tvNameDetail.text = args.name

        val textViewDescription = binding.tvDescription
        textViewDescription.text = args.description

        binding.linLayLocation.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToMapFragment(
                args.longitude, args.latitude
            )
            view.findNavController().navigate(action)
        }

    }
}