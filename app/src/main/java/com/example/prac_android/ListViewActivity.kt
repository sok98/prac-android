package com.example.prac_android

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.prac_android.databinding.ActivityListviewBinding
import com.example.prac_android.databinding.ActivityMainBinding

class ListViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뷰바인딩
        binding = ActivityListviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 아이템 리스트 준비
        val carList = ArrayList<CarForList>()
        for (i in 1 .. 10){
            carList.add(CarForList(""+i+"번째 자동차", ""+i+"순위 엔진"))
        }

        val adapter = ListViewAdapter(carList, LayoutInflater.from(this@ListViewActivity))
        binding.listviewContainer.adapter = adapter

        binding.listviewContainer.setOnItemClickListener { parent, view, position, id ->
            val carName = (adapter.getItem(position) as CarForList).name
            val carEngine = (adapter.getItem(position) as CarForList).engine

            Toast.makeText(this@ListViewActivity, carName +" "+ carEngine, Toast.LENGTH_SHORT).show()
        }
    }
}

class ListViewAdapter(
    val carForList : ArrayList<CarForList>,
    val layoutInflater: LayoutInflater
) : BaseAdapter() {
    // 뷰 리턴
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View
        val holder : ViewHolder

        if(convertView == null) {
            view = layoutInflater.inflate(R.layout.item_view, null)
            holder = ViewHolder()

            holder.carName = view.findViewById(R.id.car_name)
            holder.carEngine = view.findViewById(R.id.car_engine)

            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }
        holder.carName?.text = carForList[position].name
        holder.carEngine?.text = carForList[position].engine

        return view
    }
    // 해당 인덱스의 아이템 정보 리턴
    override fun getItem(position: Int): Any {
        return carForList[position]
    }
    // 몇번째 아이디인지 리턴
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    // 리스트가 몇개 있는지 리턴, 리스트의 전체 개수
    override fun getCount(): Int {
        return carForList.size
    }
}

class ViewHolder{
    var carName : TextView? = null
    var carEngine : TextView? = null
}
