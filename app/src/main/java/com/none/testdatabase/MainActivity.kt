package com.none.testdatabase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.none.testdatabase.databinding.ActivityMainBinding
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity()
{


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener(){
            val inputtext=binding.Text1.text.toString()
            save(inputtext)
            Toast.makeText(this,inputtext+ "is saved",Toast.LENGTH_SHORT).show()
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