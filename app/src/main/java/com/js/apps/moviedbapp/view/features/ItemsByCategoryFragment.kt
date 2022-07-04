package com.js.apps.moviedbapp.view.features

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.js.apps.moviedbapp.R
import com.js.apps.moviedbapp.databinding.ItemsByCategoryFragmentBinding
import com.js.apps.moviedbapp.model.core.ConnectivityHelper
import com.js.apps.moviedbapp.model.core.MediaTypes
import com.js.apps.moviedbapp.model.entities.media.Movie
import com.js.apps.moviedbapp.model.entities.media.Serie
import com.js.apps.moviedbapp.view.core.CardItem
import com.js.apps.moviedbapp.view.core.CardItemAdapter
import com.js.apps.moviedbapp.view.core.UIHelper
import com.js.apps.moviedbapp.viewmodel.features.ItemsByCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ItemsByCategoryFragment : Fragment() {
    private lateinit var  viewModel: ItemsByCategoryViewModel
    private lateinit var binding: ItemsByCategoryFragmentBinding
    private lateinit var uiHelper :UIHelper
    private lateinit var type : MediaTypes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.items_by_category_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.presenter = this
        viewModel = ViewModelProvider(this).get(ItemsByCategoryViewModel::class.java)
        uiHelper = UIHelper(requireContext())
        addObservers()
        type = MediaTypes.SERIE
    }

    fun seriesButtonTapped(){
        updateSelection(
            binding.seriesButton,
            binding.moviesButton,
            R.drawable.background_button_category
        )
        lifecycleScope.launch(Dispatchers.Main) {
           withContext(Dispatchers.IO){
                viewModel.discoverContents(MediaTypes.SERIE)
            }
        }
        type = MediaTypes.SERIE
    }
    fun moviesButtonTappded(){
        updateSelection(
            binding.moviesButton,
            binding.seriesButton,
            R.drawable.background_button_category
        )
        lifecycleScope.launch(Dispatchers.Main) {
            val result =  withContext(Dispatchers.IO){
                viewModel.discoverContents(MediaTypes.MOVIE)
            }
            //loadList(result)
        }
        type = MediaTypes.MOVIE
    }
    fun playNowButtonTapped(){
        updateSelection(
            binding.playNowButton,
            binding.mostPopularButton,
            R.drawable.background_button_selected
        )
        updateSelectionText(
            binding.playNowButton,
            binding.mostPopularButton,
            R.color.green_text,
            R.color.black
        )
    }
    fun mostPopularButtonTapped(){
        updateSelection(
            binding.mostPopularButton,
            binding.playNowButton,
            R.drawable.background_button_selected
        )
        updateSelectionText(
            binding.mostPopularButton,
            binding.playNowButton,
            R.color.green_text,
            R.color.black
        )
    }
    private fun updateSelection( enabled:View, disabled:View, backgroundId:Int){
        enabled.background = AppCompatResources.getDrawable(requireContext(), backgroundId)
        disabled.background = null

    }
    private fun updateSelectionText( enabled:View, disabled:View, enabledColor:Int, disabledColor:Int){
        uiHelper.setColor(enabledColor,enabled)
        uiHelper.setColor(disabledColor,disabled)
    }

    private fun checkConnectivity(){
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
        val observer = Observer<List<Movie>>{
            if(!it.isNullOrEmpty()){
                loadList(it)
                checkConnectivity()
            }
        }
       viewModel.allMovies.observe(viewLifecycleOwner,observer)
        val seriesObserver = Observer<List<Serie>>{
            if(!it.isNullOrEmpty()){
                loadList(it)
                checkConnectivity()
            }
        }
        viewModel.allSeries.observe(viewLifecycleOwner,seriesObserver)

    }
    private fun loadList( results:List<CardItem>){
        checkConnectivity()
        val adapter = CardItemAdapter(results){
            val bundle = Bundle()
            bundle.putInt("id",it.cardId())
            bundle.putInt("type",type.id)
            findNavController().navigate(R.id.action_itemsByCategory_to_itemDetail, bundle)
        }
        binding.principalRecycler.layoutManager = GridLayoutManager(
            requireContext(),
            3
        )
        binding.principalRecycler.adapter = adapter
    }

}