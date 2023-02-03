package com.example.yapechallenge.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yapechallenge.R
import com.example.yapechallenge.databinding.FragmentListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

        viewModel.loadingState.observe(viewLifecycleOwner) {
            setCustomProgressBarVisibility(it)
        }
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

        setUpObservers()
        viewModel. receiptsList.observe(viewLifecycleOwner) {
            mAdapter.setItems(it)
        }
        binding.etFilter.addTextChangedListener { userFilter ->
             viewModel.receiptsList.observe(viewLifecycleOwner) {
                 val receiptsFiltered = it.filter { receipt -> receipt.name.lowercase().contains(userFilter.toString().lowercase()) }
                 mAdapter.updateReceipts(receiptsFiltered)
             }
        }
    }

    private fun setUpObservers() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is ReceiptsViewModel.State.Success -> {
                    mAdapter.setItems(it.receipts)
                }
                is ReceiptsViewModel.State.Failure -> {
                    apiErrorView(it.cause)
                }
            }
        }
    }

    private fun apiErrorView(cause: String) {
        MaterialAlertDialogBuilder(requireActivity().applicationContext)
            .setTitle(R.string.error)
            .setMessage(cause)
            .setPositiveButton(getString(R.string.retry)) { dialog, _ ->
                viewModel.getAllTheReceipts()
                dialog.dismiss()
            }
            .show()
    }

    private fun setCustomProgressBarVisibility(state: Boolean) {
        if (state) showLoadingSpinner()
        else hideLoadingSpinner()
    }

    private fun showLoadingSpinner() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoadingSpinner() {
        binding.progressBar.visibility = View.GONE
    }

}