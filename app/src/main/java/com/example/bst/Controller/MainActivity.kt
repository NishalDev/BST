package com.example.bst.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.bst.Adapter.TimeAdapter
import com.example.bst.AddTimingTiming
import com.example.bst.R
import com.example.bst.SqliteOpenHelper
import com.example.bst.Model.TimeModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var mainRecyclerView: RecyclerView
    private lateinit var btnCreate: FloatingActionButton
    private lateinit var myDataSet: MutableList<TimeModel>
    private val dbOpenHelper = SqliteOpenHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRecyclerView = findViewById(R.id.main_recycler_view)
        btnCreate = findViewById(R.id.btn_create)
        myDataSet = dbOpenHelper.readTime()

        mainRecyclerView.adapter = TimeAdapter(this,myDataSet)
        mainRecyclerView.setHasFixedSize(true)

        btnCreate.setOnClickListener{
            val intentToAddTimeActivity = Intent(this, AddTimingTiming::class.java)
            startActivity(intentToAddTimeActivity)
            finish()
        }
    }
}