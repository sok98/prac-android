package com.example.prac_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        // GET 요청
        service.getStudentsList().enqueue(object : Callback<ArrayList<PersonFromServer>> {
            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) {
                Log.d("retrofitt", "GET ERROR : " + t.message)
            }

            override fun onResponse(
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                //통신이 잘 된 경우
                if (response.isSuccessful){
                    val personList = response.body()
                    Log.d("retrofitt", "GET res : "+personList?.get(0)?.age)

                    val code = response.code()
                    val error = response.errorBody()
                    val header = response.headers()
                }
            }
        })

//        //POST 요청(1) : createStudent
//        val params = HashMap<String, Any>()
//        params["name"] = "김개똥"
//        params["age"] = 20
//        params["intro"] = "안녕하세요"

        //POST 요청(2) : createStudentEasy
        val params = PersonFromServer(name = "김철수", age = 12, intro = "하이철수")

        //service.createStudent(params).enqueue(object : Callback<PersonFromServer> {
        service.createStudentEasy(params).enqueue(object : Callback<PersonFromServer> {

            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
                Log.d("retrofitt", "POST ERROR : " + t.message)
            }

            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if (response.isSuccessful) {
                    val person = response.body()
                    Log.d("retrofitt", "POST name : " + person?.name)
                }
            }
        })

    }
}