package com.none.testdatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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
    var last_saved=""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper =MydatabaseHelper(this,"BookStore.db",1)
//构造函数，指定名字，版本号设置为1
//        第一次创建的时候会调用onCreate的方法，显示Toast，多次点击之后数据库以及创建，不会再调用该方法。
        binding.button1.setOnClickListener(){
        /*使用文件进行存储
        *             val inputtext=binding.Text1.text.toString()
            save(inputtext)
            Toast.makeText(this,inputtext+ "is saved",Toast.LENGTH_SHORT).show()

        * */
/*/            使用SharedPreference存储元素
            val editor=getSharedPreferences("excuse_data",Context.MODE_PRIVATE).edit()
//            从content中获取sharedpreference，且标记为只有该应用程序才可以对当前进行操作
            val inputtext=binding.Text1.text.toString()*/

            val SDF=SimpleDateFormat()//实践样例
            SDF.applyPattern("yyyyMMddHHmmss")
            val date= Date()
//            刷新时间
            last_saved=date.toString()

           /* editor.putString(last_saved,inputtext)
            editor.apply()*/



        }
        binding.button2.setOnClickListener(){
            /*/          SharedPrefenrence 读取相应存储的数据
            val pref =getSharedPreferences("excuse_data",Context.MODE_PRIVATE)
            val tempString=pref.getString("last_saved","not found")
//            先获得实例，再访问元素，如没有就显示后面的备选项
            Toast.makeText(this,tempString,Toast.LENGTH_SHORT).show()*/
//            使用SQLite
            dbHelper.writableDatabase
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

class MydatabaseHelper(val context: Context,name:String,version:Int):
        SQLiteOpenHelper(context,name,null,version)
{

    private val createBook = "create table Book(" +
            "id integer primary key autoincrement," +
            "author text," + "price real," +
            "pages integer," + "name text)"
//    将建表语句定义为一个字符串常量，

    override fun onCreate(p0: SQLiteDatabase)
    {
        p0.execSQL(createBook)
        Toast.makeText(context, "Created successfully", Toast.LENGTH_SHORT).show()
//        重写建立表的onCreate方法
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int)
    {

    }
}