package com.example.yapechallenge.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yapechallenge.R
import com.example.yapechallenge.databinding.ReceiptListViewholderBinding
import model.Receipt

class ReceiptListAdapter(val onItemClick: (String)-> Unit,)
    : RecyclerView.Adapter<ReceiptListAdapter.ReceiptsViewHolder>(){

    private var mItems: List<Receipt> = listOf()

    fun setItems(items: List<Receipt>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReceiptsViewHolder(layoutInflater.inflate(R.layout.receipt_list_viewholder, parent, false))

    }

    override fun onBindViewHolder(holder: ReceiptsViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class ReceiptsViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {


        val binding = ReceiptListViewholderBinding.bind(view)

        fun bind(item: Receipt) {

                binding.tvName.text = item.name
                //imageReceipt = item.image
            }

        }
    }
