package com.example.tablayoutposition.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayoutposition.dataclass.LETTER
import com.example.tablayoutposition.R

class LetterAdapter (private val list: ArrayList<LETTER>) : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return LetterViewHolder(view)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size


    inner class LetterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val word = itemView.findViewById<TextView>(R.id.txt_word)


        fun bind(letter: LETTER) {
            word.text = letter.letter
        }
    }


}