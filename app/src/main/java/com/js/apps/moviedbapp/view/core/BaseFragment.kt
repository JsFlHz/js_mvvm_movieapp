package com.js.apps.moviedbapp.view.core

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.js.apps.moviedbapp.R
import com.js.apps.moviedbapp.model.core.ConnectivityHelper

open class BaseFragment:Fragment() {
    lateinit var connectivityHelper: ConnectivityHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectivityHelper = ConnectivityHelper(requireContext())
    }
    fun showNoInternetMessge(){
        Toast.makeText(
            requireContext(),
            getString(R.string.unavailable_on_offline_mode),
            Toast.LENGTH_LONG
        ).show()
    }
}