package com.okay.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "zyl"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager2.adapter = RecycleViewAdapter()

        viewpager2.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                Log.d(TAG,"onPageSelected--position = $position")
            }

        })

        TabLayoutMediator(tabLayout,viewpager2){tab, position ->
            tab.text = "position$position"
        }.attach()
    }


    inner class RecycleViewAdapter : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.vp_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 3
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tv?.text = "position = $position"
        }

        inner class ViewHolder : RecyclerView.ViewHolder {

            var tv: TextView? = null

            constructor(itemView: View) : super(itemView) {
                tv = itemView.findViewById<TextView>(R.id.text)
            }
        }

    }
}
