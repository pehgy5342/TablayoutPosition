package com.example.tablayoutposition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayoutposition.adapter.LetterAdapter
import com.example.tablayoutposition.adapter.NumberAdapter
import com.example.tablayoutposition.dataclass.LETTER
import com.example.tablayoutposition.dataclass.NUMBER
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvNumber = findViewById<RecyclerView>(R.id.rv_number)
        val rvLetter = findViewById<RecyclerView>(R.id.rv_letter)
        val tabs = findViewById<TabLayout>(R.id.tab)
        val scrollView = findViewById<NestedScrollView>(R.id.scrollView)
        var isUseScroll = false

        tabs.addTab(tabs.newTab().apply {
            text = "0"

        })
        tabs.addTab(tabs.newTab().apply {

            text = "50"
        })

        val lowList: ArrayList<NUMBER> = arrayListOf()
        val highList: ArrayList<LETTER> = arrayListOf()

        for (i in 0..50) {

            lowList.add(NUMBER(i.toString()))
        }

        for (i in 50..100) {
            highList.add(LETTER(i.toString()))
        }

        val numberAdapter = NumberAdapter(lowList)
        val letterAdapter = LetterAdapter(highList)
        ViewCompat.setNestedScrollingEnabled(rvNumber, false)
        ViewCompat.setNestedScrollingEnabled(rvLetter, false)

        rvNumber.apply {
            adapter = numberAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }

        rvLetter.apply {
            adapter = letterAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                isUseScroll = true
                when (tab?.text) {
                    "0" -> {

                            rvNumber.top

                        scrollView.scrollTo(0, rvNumber.top)
                    }

                    "50" -> {
                        scrollView.scrollTo(0, rvLetter.top)
                    }
                }
                tabs.setScrollPosition(tab?.position ?: 0, 0f, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                isUseScroll = true
                when (tab?.text) {
                    "0" -> {
                        scrollView.scrollTo(0, rvNumber.top)
                    }

                    "50" -> {
                        scrollView.scrollTo(0, rvLetter.top)
                    }
                }
                tabs.setScrollPosition(tab?.position ?: 0, 0f, true)
            }


        })
//        tabs.alpha = 0f
        scrollView.setOnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == 0) {
                tabs.visibility = View.GONE
                tabs.alpha = 0f

            } else {
                if (tabs.isGone) {
                    tabs.visibility = View.VISIBLE
                }
            }
        }


    }


}