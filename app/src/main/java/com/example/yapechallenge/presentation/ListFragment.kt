package com.example.yapechallenge.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yapechallenge.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import viewmodels.ReceiptsViewModel

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel by viewModels<ReceiptsViewModel>()
    private lateinit var binding: FragmentListBinding
    private lateinit var mAdapter : ReceiptListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        binding.rvReceiptsList.layoutManager = LinearLayoutManager(requireContext())
        mAdapter = ReceiptListAdapter(
            onItemClick = {
                    id -> val receipt = viewModel.getReceiptById(id)
                    val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                        receipt?.id, receipt?.image, receipt?.description, receipt?.name, receipt?.longitude, receipt?.latitude
                    )
                    view.findNavController().navigate(action)
            }
        )

        binding.rvReceiptsList.adapter = mAdapter
        viewModel.getAllTheReceipts()

        viewModel.receiptsList.observe(viewLifecycleOwner) {
            mAdapter.setItems(it)
        }

    }

}