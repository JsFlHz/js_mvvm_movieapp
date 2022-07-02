package com.js.apps.moviedbapp.view.features

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.js.apps.moviedbapp.viewmodel.features.ItemsByCategoryViewModel
import com.js.apps.moviedbapp.R

class ItemsByCategoryFragment : Fragment() {
    companion object {
        fun newInstance() = ItemsByCategoryFragment()
    }
    private lateinit var viewModel: ItemsByCategoryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.items_by_category_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemsByCategoryViewModel::class.java)
    }

}