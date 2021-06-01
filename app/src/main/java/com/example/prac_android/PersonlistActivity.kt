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
import com.example.prac_android.databinding.ActivityPersonlistBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.stream.IntStream.range

class PersonlistActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPersonlistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)
        val personList = ArrayList<personForlist>()

        // GET 요청
        service.getStudentsList().enqueue(object : Callback<ArrayList<PersonFromServer>> {
            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) {
                Log.d("retrofitt", "GET ERROR : " + t.message)
            }
            override fun onResponse(
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                if (response.isSuccessful){
                    Log.d("retrofitt", "GET OK")
                    //아이템 리스트 준비
                    val res  = response.body()
                    //for (i in range(0, res.size))
                    res?.forEach {
                        val res_name = it.name.toString()
                        val res_age = it.age.toString()
                        val res_intro = it.intro.toString()
                        personList.add(personForlist(res_name, res_age, res_intro))
                    }
                }
            }
        })

        val adapter = personlistAdapter(personList, LayoutInflater.from(this@PersonlistActivity))
        binding.personlist.adapter = adapter
        binding.personlist.layoutManager = LinearLayoutManager(this@PersonlistActivity)


    }
}

class personForlist (val name: String, val age : String, val intro: String){}

class personlistAdapter(
    val personitemList : ArrayList<personForlist>,
    val inflater : LayoutInflater
) : RecyclerView.Adapter<personlistAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personName: TextView
        val personAge: TextView
        val personIntro : TextView

        init{
            personName = itemView.findViewById(R.id.person_name)
            personAge = itemView.findViewById(R.id.person_age)
            personIntro = itemView.findViewById(R.id.person_intro)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_personlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personitemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personName.text = personitemList[position].name ?: ""
        holder.personAge.text = personitemList[position].age.toString()
        holder.personIntro.text = personitemList[position].intro ?: ""

    }
}