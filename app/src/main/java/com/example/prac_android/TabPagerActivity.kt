package com.example.prac_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.prac_android.databinding.ActivityListviewBinding
import com.example.prac_android.databinding.ActivityTabPagerBinding
import com.google.android.material.tabs.TabLayout

class TabPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ONE"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TWO"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("THREE"))

        // PagerAdapter 상속 방식
//        val adapter = ThreePagerAdapter(LayoutInflater.from(this@TabPagerActivity))
//        binding.viewPager.adapter = adapter

        // FragmentStatePagerAdapter 상속 방식
//        val adapter = ThreePagerAdapter(supportFragmentManager, 3)
//        binding.viewPager.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener  {

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 탭이 클릭됐을 때
                binding.viewPager.currentItem = tab!!.position
            }
        })

        // pager가 이동했을 때 탭을 이동시킴
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
    }
}

//class ThreePagerAdapter(
//    val layoutInflater: LayoutInflater
//) : PagerAdapter() {
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        when(position){
//            0 -> {
//                val view = layoutInflater.inflate(R.layout.fragment_test, container, false)
//                container.addView(view)
//                return view
//            }
//
//            1 -> {
//                val view = layoutInflater.inflate(R.layout.fragment_test, container, false)
//                container.addView(view)
//                return view
//            }
//
//            2 -> {
//                val view = layoutInflater.inflate(R.layout.fragment_test, container, false)
//                container.addView(view)
//                return view
//            }
//
//            else -> {
//                val view = layoutInflater.inflate(R.layout.fragment_test, container, false)
//                container.addView(view)
//                return view
//            }
//        }
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view === `object` as View
//    }
//
//    override fun getCount(): Int {
//        return 3
//    }
//}

//class ThreePagerAdapter(
//    fragmentManager: FragmentManager,
//    val tabCount : Int
//) : FragmentStatePagerAdapter(fragmentManager) {
//    override fun getItem(position: Int): Fragment {
//        return when (position){
//            0 -> Fragment1()
//            1 -> Fragment2()
//            2 -> Fragment3()
//            else -> Fragment1()
//        }
//    }
//
//    override fun getCount(): Int {
//        return tabCount
//    }
//}