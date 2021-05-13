package com.example.recyclerviewdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ItemAdapter(private val exampleList: List<ExampleItem>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem =exampleList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2

    }

    override fun getItemCount() = exampleList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        val imageView : ImageView = view.findViewById(R.id.image_view)
        val textView1: TextView= view.findViewById(R.id.text_view_1)
        val textView2: TextView= view.findViewById(R.id.text_view_2)

        init{
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)

            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }



}