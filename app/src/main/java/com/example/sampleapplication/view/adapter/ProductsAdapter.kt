package com.example.sampleapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.BR
import com.example.sampleapplication.R
import com.example.sampleapplication.model.Product
import com.example.sampleapplication.view.fragment.ProductClickInterface
import com.example.sampleapplication.view.util.getProgressDrawable
import com.example.sampleapplication.view.util.loadImage
import kotlinx.android.synthetic.main.product_list_item.view.*

/**
 * Adapter for products recycler view
 */
class ProductsAdapter(private var products: List<Product>, private val productClickInterface: ProductClickInterface) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemDataBinding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.product_list_item, parent, false)
        return ProductViewHolder(itemDataBinding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ProductViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        private var dataBinding: ViewDataBinding = binding

        fun bind(product: Product) {
            dataBinding.setVariable(BR.product, product)
            val itemView = dataBinding.root
            itemView.setOnClickListener {
                productClickInterface.onClickListener(product)
            }
            val image = itemView.productImage
            val progressDrawable = getProgressDrawable(image.context)
            image.loadImage(product.url, progressDrawable)
        }
    }

    override fun getItemCount() = products.size
}
