package com.example.bst

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.BaseColumns
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

            Log.d(TAG, time.id.toString())
            Log.d(TAG, time.time)
            Log.d(TAG, time.place)

            dialog.setPositiveButton("Update") { _, _ ->

                val intent = Intent(context, UpdataTimeActivity::class.java).apply {
                    putExtra(BaseColumns._ID, time.id)
                    putExtra(COLUMN_NAME_TIME, time.time)
                    putExtra(COLUMN_NAME_PLACE, time.place)

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

        fun deleteDialog(context: Context,time: TimeModel) {

            val dbOpenHelper = SqliteOpenHelper(context)

            val dialog = MaterialAlertDialogBuilder(context)
            dialog.setTitle("Delete")
            dialog.setMessage("Do you really want to delete?")
            dialog.setIcon(R.drawable.ic_baseline_delete_forever_24)

            dialog.setPositiveButton("Delete") { _, _ ->

                Log.d(TAG, time.id.toString())
                dbOpenHelper.deleteTime(time.id.toString())

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
