package com.none.testdatabase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.none.testdatabase.databinding.ActivityMainBinding
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity()
{


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener(){
        /*使用文件进行存储
        *             val inputtext=binding.Text1.text.toString()
            save(inputtext)
            Toast.makeText(this,inputtext+ "is saved",Toast.LENGTH_SHORT).show()

        * */
//            使用SharedPreference存储元素
            val editor=getSharedPreferences("excuse_data",Context.MODE_PRIVATE).edit()
//            从content中获取sharedpreference，且标记为只有该应用程序才可以对当前进行操作
            val inputtext=binding.Text1.text.toString()

            val SDF=SimpleDateFormat()//实践样例
            SDF.applyPattern("yyyyMMddHHmmss")
            val date= Date()
//            刷新时间


            editor.putString(date.toString(),inputtext)
            editor.apply()


        }

    }


    private fun save(inputtext:String){
        try
        {
            val output=openFileOutput("data",Context.MODE_PRIVATE)
            val writer=BufferedWriter(OutputStreamWriter(output))
            writer.use{
                it.write(inputtext)
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }
}