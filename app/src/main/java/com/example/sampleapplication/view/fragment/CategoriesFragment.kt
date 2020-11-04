package com.example.sampleapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapplication.R
import com.example.sampleapplication.model.Category
import com.example.sampleapplication.view.MainActivity
import com.example.sampleapplication.view.adapter.CategoriesAdapter
import com.example.sampleapplication.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_category_list.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation. Shows categories list.
 */
class CategoriesFragment : Fragment(),
    CategoryClickInterface {

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.categories_fragment_label)
        setupUI()
        viewModel.fetchCategories()
        observeViewModel()
    }

    private fun setupUI() {
        val layoutManager = LinearLayoutManager(context)
        categoriesList.layoutManager = layoutManager
        categoriesList.itemAnimator = DefaultItemAnimator()

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            viewModel.fetchCategories()
        }
    }

    private fun observeViewModel() {
        viewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            categories?.let {
                swipeRefreshLayout.isRefreshing = false
                categoriesList.visibility = View.VISIBLE
                val categoriesAdapter = CategoriesAdapter(it, this)
                categoriesList.adapter = categoriesAdapter
            }
        })

        viewModel.categoriesLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let { categoriesError.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    categoriesError.visibility = View.GONE
                    categoriesList.visibility = View.GONE
                }
            }
        })
    }

    override fun onClickListener(category: Category) {
        viewModel.setCategory(category)
        Navigation.findNavController(requireParentFragment().requireView()).navigate(R.id.action_CategoriesFragment_to_ProductsFragment)
    }
}
