package com.example.prac_android

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import com.example.prac_android.databinding.ActivityAsyncBinding
import java.lang.Exception

class AsyncActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAsyncBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsyncBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var task : BackgroundAsyncTask? = null
        binding.start.setOnClickListener {
            task = BackgroundAsyncTask(binding.progrssbar, binding.ment)
            task?.execute()
        }
        binding.stop.setOnClickListener{
            task?.cancel(true)
        }

    }
}

class BackgroundAsyncTask(
    val progressbar : ProgressBar,
    val progressText : TextView
) : AsyncTask<Int, Int, Int>(){
    //params : doInBackground 에서 사용할 타입 결정
    //progress : onProgressUpdate 에서 사용할 타입 결정
    //result : onPostExecute 에서 사용할 타입 결정
    var percent:Int = 0

    //실행하기 직전전
   override fun onPreExecute() {
        percent = 0
        progressbar.progress = percent
    }
    override fun doInBackground(vararg params: Int?): Int {
        while (!isCancelled){
            percent++
            if(percent > 100){
                break
            } else {
                publishProgress(percent)
            }

            //과정을 잘 보기 위해 시간 지연
            try {
                Thread.sleep(100)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        return percent
    }

    override fun onProgressUpdate(vararg values: Int?) {
        progressbar.progress = values[0] ?: 0
        progressText.text = "퍼센트 : " +  values[0]
        super.onProgressUpdate(*values)

    }

    // doInBackground가 끝났을 때 return한 타입이 들어옴
    override fun onPostExecute(result: Int?) {
        progressText.text = "작업이 완료되었습니다."
    }

    //취소버튼을 눌렀을 때
    override fun onCancelled() {
        progressbar.progress = 0
        progressText.text = "작업이 취소되었습니다."
    }
}