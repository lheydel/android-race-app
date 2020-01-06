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

    fun insertEquipe(titre: String, number: Int): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Equipe.EquipeTable.ENAME, titre)
            put(Equipe.EquipeTable.ENUM, number)
        }
        val newRowId = db?.insert(Equipe.EquipeTable.NAME, null, values)
        return newRowId
    }

    fun getEquipe(ename:String): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Equipe.EquipeTable.ENAME)

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
    fun deleteEquipe(id: String): Int {
        val db = dbHelper.writableDatabase
        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(id)
        return db.delete(Equipe.EquipeTable.NAME, selection, selectionArgs)
    }
    fun updateEquipe(oldEname: String,ename: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Equipe.EquipeTable.ENAME, ename)
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
    fun getAllEquipe(): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Equipe.EquipeTable.ENAME)

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

    fun getTeamForACourse(titre : String): Cursor? {
        val db = dbHelper.readableDatabase

        var query = "SELECT * FROM "+Equipe.EquipeTable.NAME+ " WHERE "+Equipe.EquipeTable.ENAME+
                " IN ( SELECT "+ Participe.ParticipeTable.ENAME+" FROM "+Participe.ParticipeTable.NAME+
                " WHERE "+Participe.ParticipeTable.TITLE+ " = '"+ titre + "');"

        return db.rawQuery(query, null)

    }

}