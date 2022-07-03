package com.js.apps.moviedbapp.view.features

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.js.apps.moviedbapp.viewmodel.features.ItemsByCategoryViewModel
import com.js.apps.moviedbapp.R
import com.js.apps.moviedbapp.model.entities.media.MediaItem
import com.js.apps.moviedbapp.view.core.CardItemAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemsByCategoryFragment : Fragment() {
    companion object {
        fun newInstance() = ItemsByCategoryFragment()
    }
    private lateinit var viewModel: ItemsByCategoryViewModel
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
        lifecycleScope.launch(Dispatchers.Main) {
            val result =  withContext(Dispatchers.IO){
                viewModel.discoverMovies() as (List<MediaItem>)
            }
            loadList(result)
        }
    }
    private fun loadList( results:List<MediaItem>){
        val adapter = CardItemAdapter(results){
            Log.i("tapped", it.toString())
        }
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView.adapter = adapter

    }

}