package com.example.prac_android

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.prac_android.databinding.FragmentTestBinding

class Fragment3: Fragment() {
    private lateinit var binding: FragmentTestBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(inflater, container, false)
        binding.text.setBackgroundColor(Color.GRAY)
        binding.text.text = "THREE"
        return binding.root

    }

}