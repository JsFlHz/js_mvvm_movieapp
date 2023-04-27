package com.js.apps.moviedbapp.ui.core

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.js.apps.moviedbapp.R

open class BaseFragment:Fragment() {
    lateinit var connectivityHelper: com.js.apps.moviedbapp.data.core.ConnectivityHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectivityHelper = com.js.apps.moviedbapp.data.core.ConnectivityHelper(requireContext())
        checkConnectivity()
    }
    fun checkConnectivity(){
        val connectivityHelper =
            com.js.apps.moviedbapp.data.core.ConnectivityHelper(requireContext())
        val noInternetBar = requireActivity().findViewById<TextView>(R.id.no_internet_bar)
        if(!connectivityHelper.isOnline()){
            noInternetBar?.visibility = View.VISIBLE
        }else{
            noInternetBar?.visibility = View.GONE
        }
    }
    fun showNoInternetMessge(){
        Toast.makeText(
            requireContext(),
            getString(R.string.unavailable_on_offline_mode),
            Toast.LENGTH_LONG
        ).show()
    }
}