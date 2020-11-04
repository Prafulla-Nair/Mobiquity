package com.example.sampleapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.BR
import com.example.sampleapplication.R
import com.example.sampleapplication.model.Category
import com.example.sampleapplication.view.fragment.CategoryClickInterface

/**
 * Adapter for categories recycler view
 */
class CategoriesAdapter(private var categories: List<Category>, private val categoryClickListener: CategoryClickInterface) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemDataBinding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.category_list_item, parent, false)
        return CategoryViewHolder(itemDataBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    inner class CategoryViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        private var dataBinding: ViewDataBinding = binding

        fun bind(category: Category) {
            dataBinding.setVariable(BR.productCategory, category)
            dataBinding.root.setOnClickListener {
                categoryClickListener.onClickListener(category)
            }
        }
    }

    override fun getItemCount() = categories.size
}
