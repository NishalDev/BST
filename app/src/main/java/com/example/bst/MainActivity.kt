package com.example.bst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TimeFormatException
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Time
import java.time.LocalTime
import java.util.Calendar
import java.util.SimpleTimeZone
import java.util.TimeZone

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_submit.setOnClickListener{
            addTiming()
        }
    }
    private fun addTiming(){
        val time = LocalTime.parse("09:55:00")
        val tf = TimeFormatException
        val dbHandler = SqliteOpenHelper(this,null)
        dbHandler.addTime(time.toString())
        Log.i("time:", time.toString())
    }
}