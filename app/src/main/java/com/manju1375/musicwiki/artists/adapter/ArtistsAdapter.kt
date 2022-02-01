package com.manju1375.musicwiki.artists.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manju1375.musicwiki.common.ui.adapter.BindableAdapter
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import coil.api.load
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.api.artists.model.Artist


class ArtistsAdapter(private val onArtistItemClickListener: OnArtistItemClickListener): RecyclerView.Adapter<ArtistsAdapter.ViewHolder>(), BindableAdapter<Artist> {
    private var artists: List<Artist> = listOf()
    private var recyclerView: RecyclerView? = null
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var artistName: TextView? = null
        var albumImage: AppCompatImageView? = null
        init {
                artistName =  itemView.findViewById(R.id.artist_tv)
                albumImage = itemView.findViewById(R.id.artist_iv)
            }
        fun bind(artist: Artist?) {
            artistName?.text = artist?.name
            albumImage?.load(artist?.image?.get(2)?.text){
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.black_bg)
            }
            itemView.setOnClickListener {
                onArtistItemClickListener.onItemClick(artist?.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = layoutInflater.inflate(R.layout.artist_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(artists[position])
    }

    override fun getItemCount(): Int {
       return artists.size
    }

    override fun setData(items: List<Artist>) {
        this.artists = items
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    interface OnArtistItemClickListener {
        fun onItemClick(name: String?)
    }
}