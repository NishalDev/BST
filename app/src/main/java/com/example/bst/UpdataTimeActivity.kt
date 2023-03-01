package com.example.bst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_updata_time.*

class UpdataTimeActivity : AppCompatActivity() {

    private lateinit var etUpdatedTime: TextInputLayout
    private lateinit var btnUpdate: FloatingActionButton
    private val dbOpenHelper = SqliteOpenHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updata_time)

        etUpdatedTime = findViewById(R.id.et_updated_time)
        btnUpdate = findViewById(R.id.btn_update)
        val timeOld = intent.getStringExtra(COLUMN_NAME_TIME)

        if (!timeOld.isNullOrBlank()) {
            etUpdatedTime.editText?.text =
                Editable.Factory.getInstance().newEditable(timeOld)
            Log.d("UpdateTimeActivity", timeOld.toString())
        } else {
            Log.d("UpdateTimeActivity", "value was null")
            Toast.makeText(this, "Value was null", Toast.LENGTH_SHORT).show()
        }
        btnUpdate.setOnClickListener {
            updateData()
        }
    }
    private fun updateData() {
            val id = intent.getIntExtra(BaseColumns._ID, 0).toString()
            if (etUpdatedTime.editText?.text.toString().isEmpty()) {
            etUpdatedTime.error = "Please enter your Time"
            etUpdatedTime.requestFocus()
            return
        }

        if (notEmpty()) {
            dbOpenHelper.updateTime(id,
                etUpdatedTime.editText?.text.toString(),
                etUpdatedTime.editText?.text.toString())
            Toast.makeText(this, "Updated!", Toast.LENGTH_SHORT).show()
            val intentToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }
    }
    private fun notEmpty(): Boolean {
        return (etUpdatedTime.editText?.text.toString().isNotEmpty())
    }
}
