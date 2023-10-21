package com.longkd.rcv

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: longkd
 * @Since: 15:01 - 21/10/2023
 */
class CustomItemAnimator : DefaultItemAnimator() {
    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
        holder?.itemView?.animation =
            AnimationUtils.loadAnimation(holder?.itemView?.context, R.anim.anim_fail_down)
        return super.animateRemove(holder)
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        holder?.itemView?.animation =
            AnimationUtils.loadAnimation(holder?.itemView?.context, R.anim.anim_fail_down)
        return super.animateAdd(holder)
    }
}