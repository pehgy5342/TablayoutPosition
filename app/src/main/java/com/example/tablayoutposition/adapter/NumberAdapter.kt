package com.example.tablayoutposition.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayoutposition.dataclass.NUMBER
import com.example.tablayoutposition.R

class NumberAdapter(private val list: ArrayList<NUMBER>) : RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return NumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size


    inner class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val word = itemView.findViewById<TextView>(R.id.txt_word)


        fun bind(number: NUMBER) {
            word.text = number.number
        }
    }


}