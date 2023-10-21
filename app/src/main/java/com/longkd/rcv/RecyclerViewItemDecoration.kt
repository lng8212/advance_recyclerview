package com.longkd.rcv

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: longkd
 * @Since: 00:41 - 21/10/2023
 */
class RecyclerViewItemDecoration(
    context: Context,
    resId: Int
) : RecyclerView.ItemDecoration() {

    private var mDivider: Drawable = ContextCompat.getDrawable(context, resId)!!

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val dividerLeft = 16
        val dividerRight: Int = parent.width - 16

        for (i in 0 until parent.childCount) {

            if (i != parent.childCount - 1) {
                val child: View = parent.getChildAt(i)

                val params = child.layoutParams as RecyclerView.LayoutParams

                val dividerTop: Int = child.bottom + params.bottomMargin
                val dividerBottom: Int = dividerTop + mDivider.intrinsicHeight

                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
                mDivider.draw(c)
            }
        }
    }
}
