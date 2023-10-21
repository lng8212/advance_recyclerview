package com.longkd.rcv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.longkd.rcv.databinding.ItemUserBinding
import com.longkd.rcv.model.Item

/**
 * @Author: longkd
 * @Since: 00:12 - 21/10/2023
 */
class UserAdapter(private val clickListener: ClickListener) :
    ListAdapter<Item, UserAdapter.UserViewHolder>(DefaultDiffCallback<Item>()) {
    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindDataPayloads(item: Item) {
            binding.imgFavorite.setImageResource(if (item.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favourite_unchecked)
            binding.imgFavorite.setOnClickListener {
                clickListener.onClickFavorite(layoutPosition, !item.isFavorite)
            }
        }

        fun bindData(item: Item) {
            binding.run {
                imgAvatar.setImageResource(item.image)
                txtName.text = item.name
                txtSdt.text = item.sdt
                imgFavorite.setImageResource(if (item.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favourite_unchecked)
                imgFavorite.setOnClickListener {
                    clickListener.onClickFavorite(layoutPosition, !item.isFavorite)
                }
                cbSelect.isChecked = item.isChecked
                cbSelect.setOnCheckedChangeListener { _, p1 ->
                    clickListener.onClickChecked(
                        layoutPosition,
                        p1
                    )
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = currentList.size


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = currentList[position]
        holder.bindData(item)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val item = currentList[position]
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else if (payloads[0] == true) {
            holder.bindDataPayloads(item)
        }

    }
}


interface ClickListener {
    fun onClickChecked(position: Int, isChecked: Boolean)
    fun onClickFavorite(position: Int, isClickFavorite: Boolean)
}

