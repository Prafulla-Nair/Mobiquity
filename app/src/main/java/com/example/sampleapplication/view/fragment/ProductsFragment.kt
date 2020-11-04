package com.example.sampleapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapplication.R
import com.example.sampleapplication.model.Product
import com.example.sampleapplication.view.MainActivity
import com.example.sampleapplication.view.adapter.ProductsAdapter
import com.example.sampleapplication.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_product_list.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation. Shows products list for the category selected.
 */
class ProductsFragment : Fragment(), ProductClickInterface {
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        val layoutManager = LinearLayoutManager(context)
        productsList.layoutManager = layoutManager
        productsList.itemAnimator = DefaultItemAnimator()
        val dividerItemDecoration = DividerItemDecoration(productsList.context, layoutManager.orientation)
        productsList.addItemDecoration(dividerItemDecoration)
    }

    private fun observeViewModel() {
        viewModel.selectedCategory.observe(viewLifecycleOwner, {
            val categoryName = it?.name ?: getString(R.string.product_image)
            (activity as MainActivity).supportActionBar?.title = categoryName
            productsList.visibility = View.VISIBLE
            val productsAdapter = ProductsAdapter(it.products, this)
            productsList.adapter = productsAdapter
        })
    }

    override fun onClickListener(product: Product) {
        viewModel.setProduct(product)
        Navigation.findNavController(requireParentFragment().requireView()).navigate(R.id.action_ProductsFragment_to_DetailFragment)
    }
}
