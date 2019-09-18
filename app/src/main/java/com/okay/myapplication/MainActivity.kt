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
import com.okay.myapplication.utils.ContextHelper
import com.okay.myapplication.utils.ResUtil
import com.okay.myapplication.utils.SPUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "zyl"

    override fun onCreate(savedInstanceState: Bundle?) {
        if (SPUtils.getString(this, null, "theme") == "dayTheme") {
            //默認是白天主題
            setTheme(R.style.dayTheme)
        } else {
//否则是晚上主題，這裡晚上主題我們就去加載我們晚上主題apk里的資源
            val resourceId = ResUtil.getResourceId(
                ContextHelper.getPackageContext(this, "com.okay.themeapk"),
                "style", "AppTheme"
            )
            if (resourceId!=null) setTheme(resourceId)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager2.adapter = RecycleViewAdapter()

        viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                Log.d(TAG, "onPageSelected--position = $position")
            }

        })

        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
            tab.text = "position$position"
        }.attach()

        btnTheme.setOnClickListener {
            if (SPUtils.getString(this, null, "theme") == "dayTheme") {
                SPUtils.put(this, null, "theme", "nightTheme")
            } else {
                SPUtils.put(this, null, "theme", "dayTheme")
            }
            recreate()
        }
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
