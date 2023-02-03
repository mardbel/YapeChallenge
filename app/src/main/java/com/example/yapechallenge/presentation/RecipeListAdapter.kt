package com.example.yapechallenge.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yapechallenge.R
import com.example.yapechallenge.databinding.ReceiptListViewholderBinding
import model.Recipe

class RecipeListAdapter(val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<RecipeListAdapter.RecipesViewHolder>() {

    private var mItems: List<Recipe> = listOf()

    fun setItems(items: List<Recipe>) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecipesViewHolder(
            layoutInflater.inflate(
                R.layout.receipt_list_viewholder,
                parent,
                false
            ), onItemClick
        )

    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun updateRecipes(recipeList: List<Recipe>) {
        mItems = recipeList
        notifyDataSetChanged()
    }

    class RecipesViewHolder(
        view: View,
        val onItemClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val binding = ReceiptListViewholderBinding.bind(view)

        fun bind(item: Recipe) {
            binding.tvName.text = item.name
            Glide.with(binding.imgHomeHolder.context).load(item.image).into(binding.imgHomeHolder)
            binding.root.setOnClickListener {
                onItemClick(item.id)
            }
        }
    }
}
