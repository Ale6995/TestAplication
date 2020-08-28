package com.example.testaplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listtile.view.*



class MainAdapter(val mainList: mainlist): RecyclerView.Adapter<CustomViewHolder>() {


    override fun getItemCount(): Int {
        return mainList.artist.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.listtile, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val artist = mainList.artist.get(position)
        holder?.view?.artistName?.text = artist.name
        holder?.view?.listeners?.text = "Number of listeners: "+artist.listeners
        holder?.view?.mbid?.text = "artis mbid: "+artist.mbid
        holder?.view?.url.text = "artist url: "+artist.url
        holder?.view?.streamable?.text ="is artist stremable?: "+ artist.streamable.toBoolean()


        val thumbnailImageView = holder?.view?.artistImage
        Picasso.with(holder?.view?.context).load(artist.image[1].utext).into(thumbnailImageView)


    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}














