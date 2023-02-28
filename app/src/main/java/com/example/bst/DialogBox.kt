package com.example.bst

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogBox {

        private val TAG = "DialogBox"

        fun editDialog(context:Context, time: TimeModel) {

            val dialog = MaterialAlertDialogBuilder(context)
            dialog.setTitle("Edit")
            dialog.setMessage("Do you want to update?")
            dialog.setIcon(R.drawable.ic_baseline_edit_24)

            Log.d(TAG, time.time.toString())

            dialog.setPositiveButton("Update") { _, _ ->

                val intent = Intent(context, UpdataTimeActivity::class.java).apply {
                    putExtra(COLUMN_NAME_TIME, time.time)

                }
                context.startActivity(intent)
                (context as Activity).finish()

            }

            dialog.setNeutralButton("Cancel") { _, _ ->
                Toast.makeText(context, "Cancelled!", Toast.LENGTH_SHORT).show()
            }

            dialog.create()
            dialog.setCancelable(false)
            dialog.show()
        }

        fun deleteDialog(context: Context, note: TimeModel) {

            val dbOpenHelper = SqliteOpenHelper(context)

            val dialog = MaterialAlertDialogBuilder(context)
            dialog.setTitle("Delete")
            dialog.setMessage("Do you really want to delete?")
            dialog.setIcon(R.drawable.ic_baseline_delete_forever_24)

            dialog.setPositiveButton("Delete") { _, _ ->

                Log.d(TAG, note.time.toString())
                dbOpenHelper.deleteTime(note.time.toString())

                Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()

                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                (context as Activity).finish()

            }

            dialog.setNeutralButton("Cancel") { _, _ ->
                Toast.makeText(context, "Cancelled!", Toast.LENGTH_SHORT).show()
            }

            dialog.create()
            dialog.setCancelable(false)
            dialog.show()
        }

}
