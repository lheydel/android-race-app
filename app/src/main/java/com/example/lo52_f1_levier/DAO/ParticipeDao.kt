package com.example.lo52_f1_levier.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.lo52_f1_levier.model.Participe
import com.example.lo52_f1_levier.model.CourseDbHelper

class ParticipeDao(context : Context) {
    val dbHelper = CourseDbHelper(context)

    fun insertParticipe(titre: String, numc: Int, ename : String): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Participe.ParticipeTable.NUMC, numc )
            put(Participe.ParticipeTable.TITLE, titre)
            put(Participe.ParticipeTable.ENAME, ename)
        }
        return db?.insert(Participe.ParticipeTable.NAME, null, values)
    }

    fun getParticipeByNumC(Numc:String): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.NUMC, Participe.ParticipeTable.TITLE,
            Participe.ParticipeTable.ENAME)

        val selection = "${Participe.ParticipeTable.NUMC} = ?"
        val selectionArgs = arrayOf(Numc)

        val sortOrder = "${Participe.ParticipeTable.ENAME} DESC"

        return db.query(
            Participe.ParticipeTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
    }
    fun deleteCourseByNumC(numc: String): Int {
        val db = dbHelper.writableDatabase
        val selection = "${Participe.ParticipeTable.NUMC} LIKE ?"
        val selectionArgs = arrayOf(numc)
        val deletedRows = db.delete(Participe.ParticipeTable.NAME, selection, selectionArgs)
        return deletedRows
    }
    fun updateCourseByNumC(oldNumc: String,numc: String,titre: String, ename: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Participe.ParticipeTable.NUMC, numc )
            put(Participe.ParticipeTable.TITLE, titre)
            put(Participe.ParticipeTable.ENAME, ename)
        }
        val selection = "${Participe.ParticipeTable.NUMC} LIKE ?"
        val selectionArgs = arrayOf(oldNumc)
        return db.update(
            Participe.ParticipeTable.NAME,
            values,
            selection,
            selectionArgs)
    }
}