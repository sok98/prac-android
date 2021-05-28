package com.example.prac_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class FragmentActivity : AppCompatActivity(), TestFragment.OnDataPassListener {
    override fun onDataPass(data: String?) {
        TODO("Not yet implemented")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val fragmentTest : TestFragment = TestFragment()
        val bundle : Bundle = Bundle()
        bundle.putString("hello", "hello")
        fragmentTest.arguments = bundle

        val fragmentManager : FragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragmentTest)
        fragmentTransaction.commit()
    }
}