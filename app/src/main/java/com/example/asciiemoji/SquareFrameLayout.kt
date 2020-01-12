package com.example.asciiemoji

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by Dmitry Parshin on 12.01.2020.
 */
class SquareFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}