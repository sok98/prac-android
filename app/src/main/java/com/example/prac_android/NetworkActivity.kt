package com.example.prac_android

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prac_android.databinding.ActivityNetworkBinding
import com.google.gson.Gson
import org.w3c.dom.Text
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Serializable
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNetworkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NetworkTask(
            binding.recyclerPerson,
            LayoutInflater.from(this@NetworkActivity)
        )

    }
}

class NetworkTask(
    val recyclerView: RecyclerView,
    val inflater: LayoutInflater
) : AsyncTask<Any?, Any?,Array<PersonFromServer>>() {
    override fun onPostExecute(result: Array<PersonFromServer>?) {
        val adapter = PersonAdapter(result!!, inflater)
        recyclerView.adapter = adapter
        super.onPostExecute(result)
    }

    override fun doInBackground(vararg params: Any?): Array<PersonFromServer> {
        val url : URL = URL("http://mellowcode.org/json/students/")
        val connection : HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")

        Log.d("data", ""+connection.responseCode)
        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
        }
        val data = Gson().fromJson(buffer, Array<PersonFromServer>::class.java)
        Log.d("data", "data : " + data)
        return data
    }
}

class PersonAdapter(
    val personList : Array<PersonFromServer>,
    val inflater : LayoutInflater
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView
        val age : TextView
        val intro : TextView

        init {
            name = itemView.findViewById(R.id.person_name)
            age = itemView.findViewById(R.id.person_age)
            intro = itemView.findViewById(R.id.person_intro)
        }
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_personlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = personList[position].name ?: ""
        holder.age.text = personList[position].age.toString()
        holder.intro.text = personList[position].intro ?: ""

    }
}

