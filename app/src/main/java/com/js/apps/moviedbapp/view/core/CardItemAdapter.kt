package com.js.apps.moviedbapp.view.core

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.js.apps.moviedbapp.R
import com.squareup.picasso.Picasso


class CardItemAdapter(
    private val items:List<CardItem>,
    val tapListener: (CardItem) -> Unit
):RecyclerView.Adapter<CardItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.tvDate.text = currentItem.getDate()
        holder.tvTitle.text = currentItem.getName()
       Log.i("here","${currentItem.getPoster()}")
        Picasso.get()
            .load(currentItem.getPoster())
            .into(holder.ivPoster)
        holder.itemView.setOnClickListener {
            tapListener( items[position] )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val tvTitle:TextView
        val tvDate:TextView
        val ivPoster:ImageView
        init{
            tvTitle = view.findViewById(R.id.tv_item_title)
            tvDate  = view.findViewById(R.id.tv_item_date)
            ivPoster= view.findViewById(R.id.iv_item_poster)
        }


    }


}