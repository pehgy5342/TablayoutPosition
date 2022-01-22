package com.example.tablayoutposition.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayoutposition.dataclass.LETTER
import com.example.tablayoutposition.dataclass.NUMBER
import com.example.tablayoutposition.R

class WordAdapter() : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    companion object {
        val LETTER = 0
        val NUMBER = 1
        val CHINESE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> LETTER
            1 -> NUMBER
            else -> CHINESE
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
        return when (viewType) {
            LETTER -> {
                WordViewHolder.LetterViewHolder(view)
            }
            NUMBER -> {
                WordViewHolder.NumberViewHolder(view)
            }
            else -> {
                WordViewHolder.ChineseViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = 3


    sealed class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val word = itemView.findViewById<TextView>(R.id.txt_word)

        class LetterViewHolder(itemView: View) : WordViewHolder(itemView) {
            fun bind(letter: LETTER) {
                word.text = letter.letter
            }
        }

        class NumberViewHolder(itemView: View) : WordViewHolder(itemView) {
            fun bind(number: NUMBER) {
                word.text = number.number
            }
        }

        class ChineseViewHolder(itemView: View) : WordViewHolder(itemView) {
            fun bind(chinese: String) {
                word.text = chinese
            }
        }


    }


}