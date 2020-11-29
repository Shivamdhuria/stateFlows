package com.example.stateflow.backoff

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stateflow.R
import com.example.stateflow.inflate
import com.example.stateflow.util.ImageLoader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_dog.view.*

public class RecyclerAdapter() : ListAdapter<String, RecyclerAdapter.StringViewHolder>(UserDataAdapterListDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder =
        StringViewHolder(parent.inflate(R.layout.item_dog))

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class UserDataAdapterListDiff : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    inner class StringViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(imageUrl: String) {

            with(containerView) {
                imageUrl?.let { it1 ->
                    ImageLoader.loadImage(
                        containerView.context,
                        it1,
                        image_thumbnail
                    )
                }
            }
        }
    }
}