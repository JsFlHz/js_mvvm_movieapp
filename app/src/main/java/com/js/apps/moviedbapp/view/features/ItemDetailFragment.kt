package com.js.apps.moviedbapp.view.features

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.js.apps.moviedbapp.R
import com.js.apps.moviedbapp.databinding.ItemDetailFragmentBinding
import com.js.apps.moviedbapp.model.core.MediaTypes
import com.js.apps.moviedbapp.view.core.CardItem
import com.js.apps.moviedbapp.viewmodel.features.ItemDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {
    private  val viewModel: ItemDetailViewModel by viewModels()
    private lateinit var binding:ItemDetailFragmentBinding
    private var itemId:Int = 0
    private var type:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_detail_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
           this.itemId = requireArguments().getInt("id")
           this.type = requireArguments().getInt("type")
        }
        addObservers()
        when( this.type ){
            MediaTypes.SERIE.id-> viewModel.setSerieId(itemId)
            MediaTypes.MOVIE.id-> viewModel.setVideoId(itemId)
        }

    }

    fun addObservers(){
        val movieObserver = Observer<CardItem>{
            if(it != null){
                Log.i("here","${it.toString()}")
                setContent(it)
            }
        }
        viewModel.currentMovie.observe(viewLifecycleOwner,movieObserver)
        val serieObserver = Observer<CardItem>{
            if(it != null){
                Log.i("here","${it.toString()}")
                setContent(it)
            }
        }
        viewModel.currentSerie.observe(viewLifecycleOwner,serieObserver)
    }
    private fun setContent( item:CardItem ){
        Picasso.get()
            .load(item.getBackdrop())
            .fit()
            .centerCrop()
            .into(binding.ivDetailPoster)
        binding.item = item
    }

}