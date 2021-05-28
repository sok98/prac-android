package com.example.prac_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prac_android.databinding.ActivityListviewBinding
import com.example.prac_android.databinding.ActivityRecyclerviewBinding

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 아이템 리스트 준비
        val carList = ArrayList<CarForList>()
        for (i in 1 .. 10){
            carList.add(CarForList(""+i+"번째 자동차", ""+i+"순위 엔진"))
        }

        val adapter = RecyclerViewAdapter(carList, LayoutInflater.from(this@RecyclerViewActivity))
        binding.recyclerviewContainer.adapter = adapter
        binding.recyclerviewContainer.layoutManager = LinearLayoutManager(this@RecyclerViewActivity)

    }
}

class RecyclerViewAdapter(
    val itemList : ArrayList<CarForList>,
    val inflater : LayoutInflater
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carName: TextView
        val carEngine: TextView

        init{
            carName = itemView.findViewById(R.id.car_name)
            carEngine = itemView.findViewById(R.id.car_engine)
            itemView.setOnClickListener{
                val position: Int = bindingAdapterPosition
                val enginName = itemList[position].engine
                Log.d("engine", enginName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.carName.text = itemList[position].name
        holder.carEngine.text = itemList[position].engine
    }
}