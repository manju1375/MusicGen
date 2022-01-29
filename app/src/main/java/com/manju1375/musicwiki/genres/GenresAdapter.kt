package com.manju1375.musicwiki.genres

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manju1375.musicwiki.api.genres.model.Tag
import com.manju1375.musicwiki.common.ui.adapter.BindableAdapter
import android.view.LayoutInflater
import android.widget.TextView
import com.manju1375.musicwiki.R


class GenresAdapter: RecyclerView.Adapter<GenresAdapter.ViewHolder>(), BindableAdapter<Tag> {
    private var genres: List<Tag> = listOf()
    private var recyclerView: RecyclerView? = null
    var isExpandable:Boolean? = false
    private final val EXPAND_COLLAPSE_INDEX = 10
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var genreName: TextView? = null
        init {
                genreName =  itemView.findViewById(R.id.genretv)
            }
        fun bind(name: String?,isVisible: Int) {
            genreName?.text = name
            itemView.visibility = isVisible
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = layoutInflater.inflate(R.layout.genre_tag_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var visiblity = View.VISIBLE
       isExpandable?.let {
           if(!it){
               visiblity = if(position<EXPAND_COLLAPSE_INDEX) View.VISIBLE else View.GONE
           }
       }
       holder.bind(genres[position].name,visiblity)
    }

    override fun getItemCount(): Int {
       return genres.size
    }

    override fun setData(items: List<Tag>) {
        this.genres = items
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

    fun expandRecyclerView(isExpandable: Boolean){
       this.isExpandable = isExpandable
        notifyDataSetChanged()
    }


}