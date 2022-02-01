package com.manju1375.musicwiki.albums.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manju1375.musicwiki.common.ui.adapter.BindableAdapter
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import coil.api.load
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.api.albums.model.Album


class AlbumsAdapter(private val albumItemClickListener:OnAlbumItemClickListener? = null): RecyclerView.Adapter<AlbumsAdapter.ViewHolder>(), BindableAdapter<Album> {
    private var albums: List<Album> = listOf()
    private var recyclerView: RecyclerView? = null
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var albumName: TextView? = null
        var albumImage: AppCompatImageView? = null
        init {
                albumName =  itemView.findViewById(R.id.album_tv)
                albumImage = itemView.findViewById(R.id.album_iv)
            }
        fun bind(album: Album?) {
            albumName?.text = album?.name
            albumImage?.load(album?.image?.get(2)?.text){
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.black_bg)
            }
            itemView.setOnClickListener {
                albumItemClickListener?.onItemClick(listOf(album?.name,album?.artist?.name))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = layoutInflater.inflate(R.layout.album_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(albums[position])
    }

    override fun getItemCount(): Int {
       return albums.size
    }

    override fun setData(items: List<Album>) {
        this.albums = items
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

    interface OnAlbumItemClickListener {
        fun onItemClick(albumDetailParams: List<String?>)
    }
}