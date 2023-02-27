package com.example.bst

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteOpenHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "BST.db"
        private val TABLE_TIMINGS = "timings"
        private val COLUMN_ID = "_id"
        private val COLUMN_COMPLETE_TIMING = "complete timing"
    }

    override fun onCreate(db: SQLiteDatabase?) {


        val CREATE_TIMING_TABLE = ("CREATE TABLE " + TABLE_TIMINGS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_COMPLETE_TIMING + "TEXT)")
        db?.execSQL(CREATE_TIMING_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMINGS)
        onCreate(db)
    }
    fun addTime(time: String){
        val values = ContentValues()
        values.put(COLUMN_COMPLETE_TIMING, time)
        val db = this.writableDatabase
        db.insert(TABLE_TIMINGS, null, values)
        db.close()
    }
}