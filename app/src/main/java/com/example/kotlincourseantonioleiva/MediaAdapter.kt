package com.example.kotlincourseantonioleiva

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincourseantonioleiva.MediaItem.*
import com.example.kotlincourseantonioleiva.databinding.ViewMediaItemBinding
import kotlin.properties.Delegates

class MediaAdapter(items: List<MediaItem> = emptyList(), private val listener: (MediaItem)->Unit) :
    RecyclerView.Adapter<MediaAdapter.ViewHolder>() {

    var items by Delegates.observable(items) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(parent.inflate(R.layout.view_media_item, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.itemView.setOnClickListener { listener(item) }

    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private val viewMediaItemBinding = ViewMediaItemBinding.bind(view)

        fun bind(mediaItem: MediaItem) {

            with(viewMediaItemBinding) {
                mediaTitle.text = mediaItem.title
                mediathumb.loadUrl(mediaItem.url)


                mediaVideoIndicator.setVisibleDetailVideoIndicator(mediaItem.type)
                }
            }
        }
    }


