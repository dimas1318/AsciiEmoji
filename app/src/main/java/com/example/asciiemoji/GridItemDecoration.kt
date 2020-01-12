package com.example.asciiemoji

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Dmitry Parshin on 12.01.2020.
 */
class GridItemDecoration(context: Context, @DimenRes itemOffsetId: Int) :
    RecyclerView.ItemDecoration() {

    private var mOffset: Int = context.resources.getDimensionPixelOffset(itemOffsetId)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (parent.layoutManager is GridLayoutManager) {
            val spanCount: Int = (parent.layoutManager as GridLayoutManager).spanCount
            val column = position % spanCount
            outRect.left = mOffset * (spanCount - column) / spanCount
            outRect.right = mOffset * (column + 1) / spanCount
            if (position < spanCount) {
                outRect.top = mOffset
            }
            outRect.bottom = mOffset
        }
    }
}