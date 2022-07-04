package com.js.apps.moviedbapp.view.features

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.js.apps.moviedbapp.R
import com.js.apps.moviedbapp.databinding.ItemDetailFragmentBinding
import com.js.apps.moviedbapp.model.core.MediaTypes
import com.js.apps.moviedbapp.model.entities.media.Video
import com.js.apps.moviedbapp.view.core.BaseFragment
import com.js.apps.moviedbapp.view.core.CardItem
import com.js.apps.moviedbapp.view.core.UIHelper
import com.js.apps.moviedbapp.viewmodel.features.ItemDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ItemDetailFragment : BaseFragment() {
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
        binding.presenter = this
        if(arguments != null){
           this.itemId = requireArguments().getInt("id")
           this.type = requireArguments().getInt("type")
        }
        addObservers()
        when( this.type ){
            MediaTypes.SERIE.id-> {
                binding.type = MediaTypes.SERIE
                viewModel.setSerieId(itemId)
            }
            MediaTypes.MOVIE.id-> {
                binding.type = MediaTypes.MOVIE
                viewModel.setVideoId(itemId)
            }
        }

    }

    fun addObservers(){
        val movieObserver = Observer<CardItem>{
            if(it != null){
                setContent(it)
            }
        }
        viewModel.currentMovie.observe(viewLifecycleOwner,movieObserver)
        val serieObserver = Observer<CardItem>{
            if(it != null){
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
            .into(binding.ivPoster)
        binding.item = item
    }
    fun showVideoTapped(cardItem: CardItem,type: MediaTypes){
        if(!connectivityHelper.isOnline()){
            super.showNoInternetMessge()
            return
        }
        lifecycleScope.launch (Dispatchers.Main){
            val result = withContext(Dispatchers.IO){
                viewModel.getVideos(cardItem.cardId(), type)
            }
            showVideo(result)
        }
    }
    fun showVideo(items:List<Video>){
        if(connectivityHelper.isOnline()) {
            if (items.isNotEmpty()) {
                Log.i("here", "video ${items[0].toString()}")
                binding.videoContainer.removeAllViews()
                val UiHelper = UIHelper(requireContext())
                binding.ivPoster.visibility = View.GONE
                binding.videoContainer.visibility = View.VISIBLE
                binding.videoContainer.addView(UiHelper.createWebViewVideo(items[0]))
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_video_message),
                    Toast.LENGTH_LONG
                ).show()
            }
        }else {
            super.showNoInternetMessge()
        }
    }

}