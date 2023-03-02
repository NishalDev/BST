package com.example.bst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bst.Controller.MainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout

class AddTimingTiming : AppCompatActivity() {
    private lateinit var etPlace: TextInputLayout
    private lateinit var etTime: TextInputLayout
    private lateinit var btnSubmit: FloatingActionButton
    private val dbOpenHelper = SqliteOpenHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timing)

        etPlace = findViewById(R.id.et_place)
        etTime = findViewById(R.id.et_time)
        btnSubmit = findViewById(R.id.btn_submit)

        btnSubmit.setOnClickListener {
            submitData()
        }

    }

    private fun submitData() {

        if (etTime.editText?.text.toString().isEmpty()) {
            etTime.error = "Please enter the Time"
            etTime.requestFocus()
            return
        }
        if (etPlace.editText?.text.toString().isEmpty()) {
            etPlace.error = "Please enter the Place"
            etPlace.requestFocus()
            return
        }


        if (notEmpty()) {
            dbOpenHelper.addTime(
                etTime.editText?.text.toString(),
                etPlace.editText?.text.toString())

            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
            val intentToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }
    }
    private fun notEmpty(): Boolean {
        return (etTime.editText?.text.toString().isNotEmpty() && etPlace.editText?.text.toString().isNotEmpty())
    }
}
