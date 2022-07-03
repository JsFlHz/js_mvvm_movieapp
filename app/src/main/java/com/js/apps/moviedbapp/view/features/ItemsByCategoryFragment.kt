package com.js.apps.moviedbapp.view.features

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.js.apps.moviedbapp.viewmodel.features.ItemsByCategoryViewModel
import com.js.apps.moviedbapp.R
import com.js.apps.moviedbapp.model.core.ConnectivityHelper
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import com.js.apps.moviedbapp.view.core.CardItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@AndroidEntryPoint
class ItemsByCategoryFragment : Fragment() {
    //private  val viewModel: ItemsByCategoryViewModel by viewModels<ItemsByCategoryViewModel>()
    private lateinit var  viewModel: ItemsByCategoryViewModel
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.items_by_category_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ItemsByCategoryViewModel::class.java)
        recyclerView = requireView().findViewById<RecyclerView>(R.id.principal_recycler)
        //scope sincronizado con el ciclo de veida del activity
        addObservers()

        lifecycleScope.launch(Dispatchers.Main) {
            val result =  withContext(Dispatchers.IO){
                viewModel.discoverMovies() as (List<MediaItem>)
            }
            //loadList(result)
        }
    }
    private fun checConnectivity(){
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
    private fun addObservers(){
        val observer = Observer<List<MediaItem>>{
            if(!it.isNullOrEmpty()){
                loadList(it)
                checConnectivity()
            }
        }
       viewModel.allMediaItems.observe(viewLifecycleOwner,observer)
    }
    private fun loadList( results:List<MediaItem>){
        checConnectivity()
        val adapter = CardItemAdapter(results){
            Log.i("tapped", it.toString())
        }
        recyclerView.layoutManager = GridLayoutManager(
            requireContext(),
            3
        )
        recyclerView.adapter = adapter
    }

}