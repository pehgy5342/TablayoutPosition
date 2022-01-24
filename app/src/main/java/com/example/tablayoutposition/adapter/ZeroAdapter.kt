package com.example.tablayoutposition.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayoutposition.dataclass.ZERO
import com.example.tablayoutposition.R

class ZeroAdapter(private val list: ArrayList<ZERO>) : RecyclerView.Adapter<ZeroAdapter.NumberViewHolder>() {

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


        fun bind(number: ZERO) {
            word.text = number.zero
        }
    }


}