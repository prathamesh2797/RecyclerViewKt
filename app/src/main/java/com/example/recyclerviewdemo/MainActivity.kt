package com.example.recyclerviewdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {

    private val exampleList = generateDummyList(500)
    private val adapter = ItemAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    fun insertItem(view: View){
        val index : Int = Random.nextInt(8)
        val newItem= ExampleItem(R.drawable.ic_winner, "New Item At $index", "Line 2")

        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)

    }

    fun removeItem(view: View){
        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {

        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList[position]
        clickedItem.text1 ="Clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem>{

        val list = ArrayList<ExampleItem>()

        for (i in 0 until size){
            val drawable = when(i%3){
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_audio
                else-> R.drawable.ic_winner
            }

            val item = ExampleItem(drawable,"Item $i","Line 2")

            list += item
        }
        return list

    }
}