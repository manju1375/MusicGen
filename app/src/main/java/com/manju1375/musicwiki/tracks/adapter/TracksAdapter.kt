package com.manju1375.musicwiki.tracks.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manju1375.musicwiki.common.ui.adapter.BindableAdapter
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import coil.api.load
import com.manju1375.musicwiki.R
import com.manju1375.musicwiki.api.tracks.model.Track


class TracksAdapter: RecyclerView.Adapter<TracksAdapter.ViewHolder>(), BindableAdapter<Track> {
    private var tracks: List<Track> = listOf()
    private var recyclerView: RecyclerView? = null
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var trackName: TextView? = null
        var trackImage: AppCompatImageView? = null
        init {
                trackName =  itemView.findViewById(R.id.track_tv)
                trackImage = itemView.findViewById(R.id.tracks_iv)
            }
        fun bind(track: Track?) {
            trackName?.text = track?.name
            trackImage?.load(track?.image?.get(2)?.text){
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.black_bg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = layoutInflater.inflate(R.layout.track_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(tracks[position])
    }

    override fun getItemCount(): Int {
       return tracks.size
    }

    override fun setData(items: List<Track>) {
        this.tracks = items
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
}