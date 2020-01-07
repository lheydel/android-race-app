package com.example.lo52_f1_levier.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.lo52_f1_levier.model.Coureur
import com.example.lo52_f1_levier.model.CourseDbHelper
import com.example.lo52_f1_levier.model.Participe
import java.lang.Exception

/**
 * TODO
 *
 * @constructor
 * TODO
 *
 * @param context
 */
class CoureurDao(context: Context) {
    val dbHelper = CourseDbHelper(context)
    /**
     * TODO
     *
     * @param cname
     * @param surname
     * @return
     */
    fun insertCoureur(cname: String, surname: String): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Coureur.CoureurTable.NUMC, getLastId() + 1)
            put(Coureur.CoureurTable.CNAME, cname)
            put(Coureur.CoureurTable.SURNAME, surname)
        }

        return db?.insert(Coureur.CoureurTable.NAME, null, values)
    }

    /**
     * TODO
     *
     * @param titre
     * @return
     */
    fun getCoureur(titre:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Coureur.CoureurTable.NUMC, Coureur.CoureurTable.CNAME,
            Coureur.CoureurTable.SURNAME)

        val selection = "${Coureur.CoureurTable.NUMC} = ?"
        val selectionArgs = arrayOf(titre.toString())

        val sortOrder = "${Coureur.CoureurTable.CNAME} DESC"

        return db.query(
            Coureur.CoureurTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
    }

    /**
     * TODO
     *
     * @param ID
     * @return
     */
    fun getCoureurByID(ID:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Coureur.CoureurTable.NUMC, Coureur.CoureurTable.CNAME,
            Coureur.CoureurTable.SURNAME)

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(ID.toString())

        val sortOrder = "${Coureur.CoureurTable.CNAME} DESC"

        return db.query(
            Coureur.CoureurTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
    }

    /**
     * TODO
     *
     * @param numc
     * @return
     */
    fun getCoureurByNumc(numc:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Coureur.CoureurTable.NUMC, Coureur.CoureurTable.CNAME,
            Coureur.CoureurTable.SURNAME)

        val selection = "${Coureur.CoureurTable.NUMC} = ?"
        val selectionArgs = arrayOf(numc.toString())

        val sortOrder = "${Coureur.CoureurTable.CNAME} DESC"

        return db.query(
            Coureur.CoureurTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
    }

    /**
     * TODO
     *
     * @return
     */
    fun getAllCoureur(): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Coureur.CoureurTable.NUMC, Coureur.CoureurTable.CNAME,
            Coureur.CoureurTable.SURNAME)

        val sortOrder = "${Coureur.CoureurTable.CNAME} DESC"

        return db.query(
            Coureur.CoureurTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )
    }

    /**
     * TODO
     *
     * @param numc
     * @return
     */
    fun deleteCoureur(numc: Int): Int {
        val db = dbHelper.writableDatabase
        val selection = "${Coureur.CoureurTable.NUMC} LIKE ?"
        val selectionArgs = arrayOf(numc.toString())
        val deletedRows = db.delete(Coureur.CoureurTable.NAME, selection, selectionArgs)
        return deletedRows
    }

    /**
     * TODO
     *
     * @param oldNumc
     * @param numc
     * @param cname
     * @param surname
     * @return
     */
    fun updateCoureur(oldNumc: Int,numc: Int,cname: String, surname: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Coureur.CoureurTable.NUMC, numc)
            put(Coureur.CoureurTable.CNAME,cname)
            put(Coureur.CoureurTable.SURNAME,surname)
        }
        val selection = "${Coureur.CoureurTable.NUMC} LIKE ?"
        val selectionArgs = arrayOf(oldNumc.toString())
        return db.update(
            Coureur.CoureurTable.NAME,
            values,
            selection,
            selectionArgs)
    }

    /**
     * TODO
     *
     * @param ID
     * @param numc
     * @param cname
     * @param surname
     * @return
     */
    fun updateCoureurByID(ID: Int,numc: Int,cname: String, surname: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Coureur.CoureurTable.NUMC, numc)
            put(Coureur.CoureurTable.CNAME,cname)
            put(Coureur.CoureurTable.SURNAME,surname)
        }
        val selection = "${BaseColumns._ID} LIKE ?"
        val selectionArgs = arrayOf(ID.toString())
        return db.update(
            Coureur.CoureurTable.NAME,
            values,
            selection,
            selectionArgs)
    }

    /**
     * TODO
     *
     * @return
     */
    fun getLastId() : Int {
        val db = dbHelper.writableDatabase

        var res = db.rawQuery("SELECT MAX(Numc) as maxId FROM " + Coureur.CoureurTable.NAME, null)
        res.moveToFirst()
        try{
            return (res.getString(0)).toInt()
        }catch(e: Exception){
            return -1
        }

    }

    /**
     * TODO
     *
     * @param ID
     * @return
     */
    fun getCoureurFree(ID : Int): Cursor? {
        val db = dbHelper.readableDatabase

        var query = "SELECT * FROM "+Coureur.CoureurTable.NAME+  " WHERE "+BaseColumns._ID+
                " NOT IN ( SELECT "+Participe.ParticipeTable.CR_ID+" FROM "+Participe.ParticipeTable.NAME+
                " WHERE "+Participe.ParticipeTable.C_ID+ " = "+ ID.toString() + ");"

        return db.rawQuery(query, null)

    }
}