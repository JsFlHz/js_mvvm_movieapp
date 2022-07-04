package com.js.apps.moviedbapp.view.core

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.js.apps.moviedbapp.R
import com.js.apps.moviedbapp.model.core.ConnectivityHelper

open class BaseFragment:Fragment() {
    lateinit var connectivityHelper: ConnectivityHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectivityHelper = ConnectivityHelper(requireContext())
        checkConnectivity()
    }
    fun checkConnectivity(){
        val connectivityHelper = ConnectivityHelper(requireContext())
        val noInternetBar = requireActivity().findViewById<TextView>(R.id.no_internet_bar)
        if(!connectivityHelper.isOnline()){
            Log.i("here", "find noInternetBar?${noInternetBar}")
            noInternetBar?.visibility = View.VISIBLE
        }else{
            noInternetBar?.visibility = View.GONE
        }
        Log.i("here", "is on line?${connectivityHelper.isOnline()}")
    }
    fun showNoInternetMessge(){
        Toast.makeText(
            requireContext(),
            getString(R.string.unavailable_on_offline_mode),
            Toast.LENGTH_LONG
        ).show()
    }
}