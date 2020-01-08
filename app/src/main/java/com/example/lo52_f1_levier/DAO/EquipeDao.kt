package com.example.lo52_f1_levier.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.lo52_f1_levier.model.Equipe
import com.example.lo52_f1_levier.model.CourseDbHelper
import com.example.lo52_f1_levier.model.Participe

class EquipeDao(context : Context) {
    val dbHelper = CourseDbHelper(context)

    fun insertEquipe(titre: String, enum : Int): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Equipe.EquipeTable.ENAME, titre)
            put(Equipe.EquipeTable.ENUM, enum)
        }
        val newRowId = db?.insert(Equipe.EquipeTable.NAME, null, values)
        return newRowId
    }

    fun getEquipe(ename:String): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Equipe.EquipeTable.ENAME, Equipe.EquipeTable.ENUM)

        val selection = "${Equipe.EquipeTable.ENAME} = ?"
        val selectionArgs = arrayOf(ename)


        val cursor = db.query(
            Equipe.EquipeTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null
        )

        return cursor
    }

    fun getEquipeByID(ID:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Equipe.EquipeTable.ENAME, Equipe.EquipeTable.ENUM)

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(ID.toString())


        val cursor = db.query(
            Equipe.EquipeTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null
        )

        return cursor
    }

    fun deleteEquipe(ename: String): Int {
        val db = dbHelper.writableDatabase
        val selection = "${BaseColumns._ID} LIKE ?"
        val selectionArgs = arrayOf(ename)
        return db.delete(Equipe.EquipeTable.NAME, selection, selectionArgs)
    }
    fun deleteEquipeByID(ID: Int): Int {
        val db = dbHelper.writableDatabase
        val selection = "${BaseColumns._ID} LIKE ?"
        val selectionArgs = arrayOf(ID.toString())
        return db.delete(Equipe.EquipeTable.NAME, selection, selectionArgs)
    }
    fun updateEquipe(oldEname: String,ename: String, enum : Int): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Equipe.EquipeTable.ENAME, ename)
            put(Equipe.EquipeTable.ENUM, enum)
        }
        val selection = "${Equipe.EquipeTable.ENAME} LIKE ?"
        val selectionArgs = arrayOf(oldEname)
        val count = db.update(
            Equipe.EquipeTable.NAME,
            values,
            selection,
            selectionArgs)
        return count
    }
    fun updateEquipeByID(ID: Int,ename: String, enum : Int): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Equipe.EquipeTable.ENAME, ename)
            put(Equipe.EquipeTable.ENUM, enum)
        }
        val selection = "${BaseColumns._ID} LIKE ?"
        val selectionArgs = arrayOf(ID.toString())
        val count = db.update(
            Equipe.EquipeTable.NAME,
            values,
            selection,
            selectionArgs)
        return count
    }
    fun getAllEquipe(): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Equipe.EquipeTable.ENAME, Equipe.EquipeTable.ENUM)

        val cursor = db.query(
            Equipe.EquipeTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null
        )

        return cursor
    }

    fun getTeamForACourse(ID : Int): Cursor? {
        val db = dbHelper.readableDatabase

        var query = "SELECT * FROM "+Equipe.EquipeTable.NAME+ " WHERE "+BaseColumns._ID +
                " IN ( SELECT "+ Participe.ParticipeTable.E_ID+" FROM "+Participe.ParticipeTable.NAME+
                " WHERE "+Participe.ParticipeTable.C_ID+  " = "+ ID.toString() + ");"

        return db.rawQuery(query, null)

    }

}