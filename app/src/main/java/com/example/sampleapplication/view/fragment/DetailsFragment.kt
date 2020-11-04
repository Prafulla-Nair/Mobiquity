package com.example.sampleapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.sampleapplication.R
import com.example.sampleapplication.view.util.getProgressDrawable
import com.example.sampleapplication.view.util.loadImage
import com.example.sampleapplication.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_details.view.*

/**
 * A simple [Fragment] subclass as the third destination in the navigation. Shows product details information.
 */
class DetailsFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var dataBinding: ViewDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.fragment_details, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.detail_fragment_label)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.selectedProduct.observe(viewLifecycleOwner, {
            dataBinding.setVariable(BR.productDetails, it)
            val image = dataBinding.root.productImageView
            val progressDrawable = getProgressDrawable(image.context)
            image.loadImage(it.url, progressDrawable) })
    }
}
