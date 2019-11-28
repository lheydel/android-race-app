package com.example.lo52_f1_levier.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.lo52_f1_levier.model.Course
import com.example.lo52_f1_levier.model.CourseDbHelper

class CourseDao(context: Context) {
    val dbHelper = CourseDbHelper(context)

    fun insertCourse(titre: String, date: String): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Course.Coursetable.TITLE, titre)
            put(Course.Coursetable.DATE, date)
        }
        return db?.insert(Course.Coursetable.TABLE_NAME, null, values)
    }

    fun getCourse(titre:String): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Course.Coursetable.TITLE, Course.Coursetable.DATE)

        val selection = "${Course.Coursetable.TITLE} = ?"
        val selectionArgs = arrayOf(titre)

        val sortOrder = "${Course.Coursetable.DATE} DESC"

        return db.query(
            Course.Coursetable.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
    }
    fun deleteCourse(titre: String): Int {
        val db = dbHelper.writableDatabase
        val selection = "${Course.Coursetable.TITLE} LIKE ?"
        val selectionArgs = arrayOf(titre)
        val deletedRows = db.delete(Course.Coursetable.TABLE_NAME, selection, selectionArgs)
        return deletedRows
    }
    fun updateCourse(oldTitle: String,title: String,date: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Course.Coursetable.TITLE, title)
            put(Course.Coursetable.DATE,date)
        }
        val selection = "${Course.Coursetable.TITLE} LIKE ?"
        val selectionArgs = arrayOf(oldTitle)
        return db.update(
            Course.Coursetable.TABLE_NAME,
            values,
            selection,
            selectionArgs)
    }

}