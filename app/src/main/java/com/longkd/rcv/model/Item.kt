package com.longkd.rcv.model

import androidx.annotation.DrawableRes

/**
 * @Author: longkd
 * @Since: 23:58 - 20/10/2023
 */

data class Item(
    val name: String,
    val sdt: String,
    @DrawableRes val image: Int,
    var isFavorite: Boolean,
    var isChecked: Boolean = false
)