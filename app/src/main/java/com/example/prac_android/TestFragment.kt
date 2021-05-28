package com.example.prac_android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class TestFragment:Fragment(){

    interface OnDataPassListener {
        fun onDataPass(data: String?)
    }

    lateinit var dataPassListener : OnDataPassListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        dataPassListener = context as OnDataPassListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataPassListener.onDataPass("Good Bye")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val data = arguments?.getString("hello")
        super.onActivityCreated(savedInstanceState)
    }
}