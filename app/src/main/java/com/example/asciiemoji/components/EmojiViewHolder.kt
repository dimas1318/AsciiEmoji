package com.example.asciiemoji.components

import android.view.View
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.asciiemoji.model.Emoji
import kotlinx.android.synthetic.main.emoji_item_view.view.*

/**
 * Created by Dmitry Parshin on 12.01.2020.
 */


class EmojiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(emoji: Emoji, listener: OnEmojiClickListener) {
        itemView.description_text_view.text = emoji.text
        itemView.emoji_text_view.text = emoji.emoji
        TextViewCompat.setAutoSizeTextTypeWithDefaults(
            itemView.emoji_text_view,
            TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
        )

        itemView.setOnClickListener { listener.onEmojiClick(emoji) }
    }
}