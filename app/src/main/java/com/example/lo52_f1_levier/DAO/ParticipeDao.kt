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
            Participe.ParticipeTable.ENAME,
            Participe.ParticipeTable.TIME1,
            Participe.ParticipeTable.TIME2,
            Participe.ParticipeTable.TIME3,
            Participe.ParticipeTable.TIME4,
            Participe.ParticipeTable.TIME5,
            Participe.ParticipeTable.TIME6,
            Participe.ParticipeTable.TIME7,
            Participe.ParticipeTable.TIME8,
            Participe.ParticipeTable.TIME9,
            Participe.ParticipeTable.TIME10
            )

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

    fun getParticipeByEname(Ename:String): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.NUMC, Participe.ParticipeTable.TITLE,
            Participe.ParticipeTable.ENAME,
            Participe.ParticipeTable.TIME1,
            Participe.ParticipeTable.TIME2,
            Participe.ParticipeTable.TIME3,
            Participe.ParticipeTable.TIME4,
            Participe.ParticipeTable.TIME5,
            Participe.ParticipeTable.TIME6,
            Participe.ParticipeTable.TIME7,
            Participe.ParticipeTable.TIME8,
            Participe.ParticipeTable.TIME9,
            Participe.ParticipeTable.TIME10
        )

        val selection = "${Participe.ParticipeTable.ENAME} = ?"
        val selectionArgs = arrayOf(Ename)

        val sortOrder = "${Participe.ParticipeTable.NUMC} DESC"

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

    fun getParticipeByTitle(Title:String): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.NUMC, Participe.ParticipeTable.TITLE,
            Participe.ParticipeTable.ENAME,
            Participe.ParticipeTable.TIME1,
            Participe.ParticipeTable.TIME2,
            Participe.ParticipeTable.TIME3,
            Participe.ParticipeTable.TIME4,
            Participe.ParticipeTable.TIME5,
            Participe.ParticipeTable.TIME6,
            Participe.ParticipeTable.TIME7,
            Participe.ParticipeTable.TIME8,
            Participe.ParticipeTable.TIME9,
            Participe.ParticipeTable.TIME10
        )

        val selection = "${Participe.ParticipeTable.TITLE} = ?"
        val selectionArgs = arrayOf(Title)

        val sortOrder = "${Participe.ParticipeTable.NUMC} DESC"

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

    fun getAllParticipe(): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.NUMC, Participe.ParticipeTable.TITLE,
            Participe.ParticipeTable.ENAME,
            Participe.ParticipeTable.TIME1,
            Participe.ParticipeTable.TIME2,
            Participe.ParticipeTable.TIME3,
            Participe.ParticipeTable.TIME4,
            Participe.ParticipeTable.TIME5,
            Participe.ParticipeTable.TIME6,
            Participe.ParticipeTable.TIME7,
            Participe.ParticipeTable.TIME8,
            Participe.ParticipeTable.TIME9,
            Participe.ParticipeTable.TIME10
        )



        val sortOrder = "${Participe.ParticipeTable.NUMC} DESC"

        return db.query(
            Participe.ParticipeTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
    }

    fun deleteParticipeByNumC(numc: String): Int {
        val db = dbHelper.writableDatabase
        val selection = "${Participe.ParticipeTable.NUMC} LIKE ?"
        val selectionArgs = arrayOf(numc)
        val deletedRows = db.delete(Participe.ParticipeTable.NAME, selection, selectionArgs)
        return deletedRows
    }

    fun updateParticipeByNumC(oldNumc: String,numc: String,titre: String, ename: String): Int {
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

    fun setTimeByNumC(Numc: String,numTime : Int, time : Int): Int {
        val db = dbHelper.writableDatabase
        val values=ContentValues()
        when(numTime){
            1->  values.apply {
            put(Participe.ParticipeTable.TIME1, time)
        }
            2->  values.apply {
                put(Participe.ParticipeTable.TIME2, time)
            }
            3->  values.apply {
                put(Participe.ParticipeTable.TIME3, time)
            }
            4->  values.apply {
                put(Participe.ParticipeTable.TIME4, time)
            }
            5->  values.apply {
                put(Participe.ParticipeTable.TIME5, time)
            }
            6->  values.apply {
                put(Participe.ParticipeTable.TIME6, time)
            }
            7->  values.apply {
                put(Participe.ParticipeTable.TIME7, time)
            }
            8->  values.apply {
                put(Participe.ParticipeTable.TIME8, time)
            }
            9->  values.apply {
                put(Participe.ParticipeTable.TIME9, time)
            }
            10->  values.apply {
                put(Participe.ParticipeTable.TIME10, time)
            }
            else -> values.apply {
            put(Participe.ParticipeTable.TIME1, time)
        }

        }

        val selection = "${Participe.ParticipeTable.NUMC} LIKE ?"
        val selectionArgs = arrayOf(Numc)
        return db.update(
            Participe.ParticipeTable.NAME,
            values,
            selection,
            selectionArgs)
    }
}