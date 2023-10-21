package com.longkd.rcv

import androidx.recyclerview.widget.DiffUtil
import com.longkd.rcv.model.Item

/**
 * @Author: longkd
 * @Since: 00:18 - 21/10/2023
 */
class DefaultDiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return if (oldItem is Item && newItem is Item){
            oldItem.name == newItem.name
        } else
            false
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return if (oldItem is Item && newItem is Item){
            oldItem == newItem
        } else
            false
    }

    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        if (oldItem is Item && newItem is Item) {
          return  oldItem.isFavorite != newItem.isFavorite
        }
        return null
    }
}