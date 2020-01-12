package com.example.asciiemoji.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asciiemoji.R
import com.example.asciiemoji.model.Emoji

/**
 * Created by Dmitry Parshin on 12.01.2020.
 */
class EmojiAdapter(val listener: OnEmojiClickListener) : RecyclerView.Adapter<EmojiViewHolder>() {

    private var mEmojies: List<Emoji> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmojiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.emoji_item_view, parent, false)
        return EmojiViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: EmojiViewHolder,
        position: Int
    ) {
        holder.bind(mEmojies[position], this.listener)
    }

    override fun getItemCount() = mEmojies.size

    fun setDate(emojies: List<Emoji>?) {
        mEmojies = emojies?.toList() ?: emptyList()
        notifyDataSetChanged()
    }
}