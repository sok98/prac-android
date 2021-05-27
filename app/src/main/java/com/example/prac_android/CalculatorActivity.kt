package com.example.prac_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.prac_android.databinding.ActivityCalculatorBinding


class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sum = 0
        var new = ""

        fun changeNumber(n:String){
            new += n
            binding.number.text = new
        }

        binding.one.setOnClickListener{
            changeNumber("1")
        }
        binding.two.setOnClickListener{
            changeNumber("2")
        }
        binding.three.setOnClickListener{
            changeNumber("3")
        }
        binding.four.setOnClickListener{
            changeNumber("4")
        }
        binding.five.setOnClickListener{
            changeNumber("5")
        }
        binding.six.setOnClickListener{
            changeNumber("6")
        }
        binding.seven.setOnClickListener{
            changeNumber("7")
        }
        binding.eight.setOnClickListener{
            changeNumber("8")
        }
        binding.nine.setOnClickListener{
            changeNumber("9")
        }
        binding.zero.setOnClickListener{
            changeNumber("0")
        }

        binding.ca.setOnClickListener{
            binding.number.text = "0"
            sum = 0
            new = ""
        }
        binding.plus.setOnClickListener {
            sum += new.toInt()
            new = ""
            binding.number.text = sum.toString()
        }


    }
}