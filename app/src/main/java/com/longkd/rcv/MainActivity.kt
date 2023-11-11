package com.longkd.rcv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.longkd.rcv.databinding.ActivityMainBinding
import com.longkd.rcv.model.Item

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private var currentType = Layout.LINEAR
    private lateinit var itemDecoration: RecyclerViewItemDecoration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initData()
        initAction()
    }


    private fun initView() {
        itemDecoration = RecyclerViewItemDecoration(this, R.drawable.item_divider)
        userAdapter = UserAdapter(object : ClickListener {
            override fun onClickChecked(position: Int, isChecked: Boolean) {
                val list = userAdapter.currentList
                val newList = list.mapIndexed { pos, item ->
                    if (pos == position) {
                        item.copy(isChecked = isChecked)
                    } else {
                        item
                    }
                }
                userAdapter.submitList(newList)
            }

            override fun onClickFavorite(position: Int, isClickFavorite: Boolean) {
                val list = userAdapter.currentList
                val newList = list.mapIndexed { pos, item ->
                    if (pos == position) {
                        item.copy(isFavorite = isClickFavorite)
                    } else {
                        item
                    }
                }
                userAdapter.submitList(newList)
            }

        })
        binding.rcvList.run {
            adapter = userAdapter
            addItemDecoration(itemDecoration)
            itemAnimator = CustomItemAnimator()
        }


    }

    private fun initData() {
        userAdapter.submitList( initList())

    }

    private fun initAction() {
        binding.btnSwitch.setOnClickListener {
            when (currentType) {
                Layout.LINEAR -> {
                    binding.btnSwitch.setImageResource(R.drawable.ic_grid)
                    binding.rcvList.run {
                        removeItemDecoration(itemDecoration)
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                    }
                    currentType = Layout.GRID
                }

                Layout.GRID -> {
                    binding.btnSwitch.setImageResource(R.drawable.ic_linear)
                    binding.rcvList.run {
                        addItemDecoration(itemDecoration)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                    currentType = Layout.LINEAR
                }
            }
        }
        binding.btnDelete.setOnClickListener {
            val list = userAdapter.currentList
            val newList = list.filter {
                !it.isChecked
            }
            userAdapter.submitList(newList)
        }
        binding.btnAdd.setOnClickListener {
            val newList = userAdapter.currentList.toMutableList()
            newList.add(
                Item(
                    "Long-${System.currentTimeMillis().toString().reversed()}",
                    "0123344",
                    R.drawable.ic_launcher_background,
                    false
                )
            )
            userAdapter.submitList(newList)
        }
    }

    private fun initList(): List<Item> {
        val list = mutableListOf<Item>()
        for (i in 0 until 5) {
            list.add(
                Item(
                    "Long-${System.currentTimeMillis().toString().reversed() + i}",
                    "0123344",
                    R.drawable.ic_launcher_background,
                    false
                )
            )
        }
        return list

    }
}

enum class Layout {
    GRID,
    LINEAR
}