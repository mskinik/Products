package com.mskinik.products.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mskinik.products.databinding.ItemHomeProductBinding
import com.mskinik.products.domain.model.Product
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class HomeProductAdapter @Inject constructor(): ListAdapter<Product, HomeProductAdapter.HomeViewHolder>(NewsListComparator()) {
    private var onItemClickListener: (Int) -> Unit = {}
    fun setClickListener(onItemClickListener: (Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeViewHolder(private val binding:ItemHomeProductBinding ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) = with(binding) {
            with(item) {
                Log.d("TAG", "bind: girdi1 item: $item")
                product = item
            }
            root.setOnClickListener {
                item.id?.let { it1 -> onItemClickListener.invoke(it1) }
            }
        }
    }
}

class NewsListComparator : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldProduct: Product, newProduct: Product): Boolean =
        oldProduct.id == newProduct.id

    override fun areContentsTheSame(oldProduct: Product, newProduct: Product): Boolean =
        oldProduct == newProduct

}