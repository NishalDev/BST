package com.example.bst

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log
import android.widget.Toast
import com.example.bst.TimeModel
import com.example.bst.COLUMN_NAME_TIME
import com.example.bst.TABLE_NAME


private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_NAME_TIME TEXT,"+
            "$COLUMN_NAME_PLACE TEXT)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

class SqliteOpenHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    companion object {
        // If you change the database schema, you must increment the database version.
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "BusTime.dp"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun addTime(time: String, place: String) {

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME_TIME, time)
            put(COLUMN_NAME_PLACE, place)
        }
        db?.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun readTime(): MutableList<TimeModel> {

        val db = this.readableDatabase
        val cursorTimes: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val TimeList: MutableList<TimeModel> = mutableListOf()

        if (cursorTimes.moveToFirst()) {
            do {
                Log.d("DPOpenHelper", cursorTimes.getString(0))
                TimeList.add(
                    TimeModel(
                        cursorTimes.getInt(0),
                        cursorTimes.getString(1),
                        cursorTimes.getString(2)
                    )
                )
            } while (cursorTimes.moveToNext())
        }
        cursorTimes.close()
        return TimeList
    }

    fun updateTime(id: String, time:String, place: String) {

        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME_TIME, time)
            put(COLUMN_NAME_PLACE, place)
        }
        try {
            db?.update(TABLE_NAME, values, "_id = ?", arrayOf(id))
            db.close()
        } catch (e: Exception) {
            Log.d("DBOpenHelper", e.message.toString())
        }
    }

    fun deleteTime(id:String) {

        val db = this.writableDatabase
        try {
            db?.delete(TABLE_NAME,"_id = ?", arrayOf(id))
            db.close()
        } catch (e: Exception) {
            Log.d("DBOpenHelper", e.message.toString())
        }
    }
}