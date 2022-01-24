package com.example.tablayoutposition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayoutposition.adapter.FiftyAdapter
import com.example.tablayoutposition.adapter.ZeroAdapter
import com.example.tablayoutposition.dataclass.FIFTY
import com.example.tablayoutposition.dataclass.ZERO
import com.google.android.material.tabs.TabLayout
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvZero = findViewById<RecyclerView>(R.id.rv_zero)
        val rvFifty = findViewById<RecyclerView>(R.id.rv_fifty)
        val tabs = findViewById<TabLayout>(R.id.tab)
        val scrollView = findViewById<NestedScrollView>(R.id.scrollView)
        val layoutOne = findViewById<TextView>(R.id.layout_one)
        var isUserScroll = false

        tabs.addTab(tabs.newTab().apply {
            text = "零"

        })
        tabs.addTab(tabs.newTab().apply {

            text = "五十"
        })

        val lowList: ArrayList<ZERO> = arrayListOf()
        val highList: ArrayList<FIFTY> = arrayListOf()

        for (i in 0..49) {
            lowList.add(ZERO(i.toString()))
        }

        for (i in 50..100) {
            highList.add(FIFTY(i.toString()))
        }

        val numberAdapter = ZeroAdapter(lowList)
        val letterAdapter = FiftyAdapter(highList)
        ViewCompat.setNestedScrollingEnabled(rvZero, false)
        ViewCompat.setNestedScrollingEnabled(rvFifty, false)

        rvZero.apply {
            setHasFixedSize(false)
            adapter = numberAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

        }

        rvFifty.apply {
            setHasFixedSize(false)
            adapter = letterAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            //tab被選中時
            override fun onTabSelected(tab: TabLayout.Tab?) {
                isUserScroll = true
                when (tab?.text) {
                    "0" -> {
                        val dy = if (rvZero.isVisible) {
                            rvZero.top
                        } else {
                            0
                        }
                        scrollView.scrollTo(0, dy - tabs.height + 20)
                    }
                    "50" -> {
                        scrollView.scrollTo(0, rvFifty.top - tabs.height + 20)
                    }
                }
                tabs.setScrollPosition(tab?.position ?: 0, 0f, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                isUserScroll = true
                when (tab?.text) {
                    "0" -> {
                        val dy = if (rvZero.isVisible) {
                            rvZero.top
                        } else {
                            0
                        }
                        scrollView.scrollTo(0, dy - tabs.height + 20)
                    }

                    "50" -> {
                        scrollView.scrollTo(0, rvFifty.top - tabs.height + 20)
                    }
                }
                tabs.setScrollPosition(tab?.position ?: 0, 0f, true)
            }
        })
        //全透明
        tabs.alpha = 0f
        scrollView.setOnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == 0) {
                tabs.visibility = View.GONE
                //全透明
                tabs.alpha = 0f
                return@setOnScrollChangeListener
            } else {
                if (tabs.isGone) {
                    tabs.visibility = View.VISIBLE
                }
                //由透明漸漸顯示
                tabs.alpha = abs((scrollY.toFloat() + tabs.height) / layoutOne.bottom.toFloat())
            }
            if (isUserScroll) {
                isUserScroll = false
                return@setOnScrollChangeListener
            }
            v.top.let {
                val calculationScrollY = scrollY.toFloat() + tabs.height
                //0
                val zero = when {
                    rvZero.top != 0 && rvZero.isVisible -> {
                        rvZero.top
                    }
                    else -> 0
                }
                //50
                val fifty = if (rvFifty.isVisible) {
                    rvFifty.top
                } else {
                    0
                }

                val topList = arrayListOf(zero, fifty).filter { it > 0 }
                val topSize = topList.size - 1
                topList.mapIndexed { index, top ->
                    if (index < topSize) {
                        if (calculationScrollY > top && calculationScrollY < topList[index + 1]) {
                            tabs.setScrollPosition(index, 0f, true)
                            return@setOnScrollChangeListener
                        }
                    } else {
                        if (calculationScrollY > top) {
                            tabs.setScrollPosition(index, 0f, true)
                            return@setOnScrollChangeListener
                        }
                    }
                }
            }
        }
    }
}