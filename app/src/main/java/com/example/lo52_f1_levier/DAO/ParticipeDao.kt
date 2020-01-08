package com.example.lo52_f1_levier.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.lo52_f1_levier.model.Coureur
import com.example.lo52_f1_levier.model.Participe
import com.example.lo52_f1_levier.model.CourseDbHelper

/**
 * ensemble des fonction permettant de modifier le contenue de la base participe
 *
 * @constructor
 * TODO
 *
 * @param context
 */
class ParticipeDao(context : Context) {
    val dbHelper = CourseDbHelper(context)
    /**
     * Permet l'insertion dans la table participe
     *
     * @param C_ID
     * @param CR_ID
     * @param E_ID
     * @return
     */
    fun insertParticipe(C_ID: Int, CR_ID: Int, E_ID : Int): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Participe.ParticipeTable.CR_ID, CR_ID )
            put(Participe.ParticipeTable.C_ID, C_ID)
            put(Participe.ParticipeTable.E_ID, E_ID)
        }
        return db?.insert(Participe.ParticipeTable.NAME, null, values)
    }

    /**
     * Renvoie les enregistrements dont l'id du coureur = CR_ID
     *
     * @param CR_ID
     * @return cursor
     */
    fun getParticipeByCR_ID(CR_ID:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.CR_ID, Participe.ParticipeTable.C_ID,
            Participe.ParticipeTable.E_ID,
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

        val selection = "${Participe.ParticipeTable.CR_ID} = ?"
        val selectionArgs = arrayOf(CR_ID.toString())

        val sortOrder = "${Participe.ParticipeTable.E_ID} DESC"

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

    /**
     * Renvoie un enregistrement de la table participe  via leur ID
     *
     * @param ID
     * @return cursor
     */
    fun getParticipeByID(ID:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.CR_ID, Participe.ParticipeTable.C_ID,
            Participe.ParticipeTable.E_ID,
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

        val selection = "${BaseColumns._ID} = ?"
        val selectionArgs = arrayOf(ID.toString())

        val sortOrder = "${Participe.ParticipeTable.E_ID} DESC"

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

    /**
     *Renvoie les enregistrements dont l'id de l'équipe  = E_ID
     *
     * @param E_ID
     * @return
     */
    fun getParticipeByE_ID(E_ID:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.CR_ID, Participe.ParticipeTable.C_ID,
            Participe.ParticipeTable.E_ID,
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

        val selection = "${Participe.ParticipeTable.E_ID} = ?"
        val selectionArgs = arrayOf(E_ID.toString())

        val sortOrder = "${Participe.ParticipeTable.CR_ID} DESC"

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

    /**
     * Renvoie les enregistrements dont l'id de la course  = C_ID
     *
     * @param C_ID
     * @return cursor
     */
    fun getParticipeByC_ID(C_ID:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.CR_ID, Participe.ParticipeTable.C_ID,
            Participe.ParticipeTable.E_ID,
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

        val selection = "${Participe.ParticipeTable.C_ID} = ?"
        val selectionArgs = arrayOf(C_ID.toString())

        val sortOrder = Participe.ParticipeTable.E_ID  // important for TeamBoxGridAdapter

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

    /**
     * Renvoie les enregistrements dont l'id de la course  = C_ID et l'id de l'équipe =E_ID
     *
     * @param C_ID
     * @param E_ID
     * @return cursor
     */
    fun getParticipeByC_ID_E_ID(C_ID:Int,E_ID: Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.CR_ID, Participe.ParticipeTable.C_ID,
            Participe.ParticipeTable.E_ID,
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

        val selection = "${Participe.ParticipeTable.C_ID} = ? AND ${Participe.ParticipeTable.E_ID} = ?"
        val selectionArgs = arrayOf(C_ID.toString(), E_ID.toString())

        val sortOrder = "${Participe.ParticipeTable.CR_ID} DESC"

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

    /**
     * Revoie tout les enregistrement dans participe
     *
     * @return
     */
    fun getAllParticipe(): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.CR_ID, Participe.ParticipeTable.C_ID,
            Participe.ParticipeTable.E_ID,
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



        val sortOrder = "${Participe.ParticipeTable.CR_ID} DESC"

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

    /**
     * Supprime les enregistrements dont l'id du coureur = CR_ID
     *
     * @param CR_ID
     * @return
     */
    fun deleteParticipeByCR_ID(CR_ID: Int): Int {
        val db = dbHelper.writableDatabase
        val selection = "${Participe.ParticipeTable.CR_ID} = ?"
        val selectionArgs = arrayOf(CR_ID.toString())
        val deletedRows = db.delete(Participe.ParticipeTable.NAME, selection, selectionArgs)
        return deletedRows
    }

    fun deleteParticipeByCourseIdAndTeamId(C_ID: Int, E_ID: Int): Int {
        val db = dbHelper.writableDatabase
        val selection = "${Participe.ParticipeTable.C_ID} = ? AND ${Participe.ParticipeTable.E_ID} = ?"
        val selectionArgs = arrayOf(C_ID.toString(),E_ID.toString())
        val deletedRows = db.delete(Participe.ParticipeTable.NAME, selection, selectionArgs)
        return deletedRows
    }

    fun setTimeByRunnerId(CR_ID: Int, numTime : Int, time : Long): Int {
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

        val selection = "${Participe.ParticipeTable.CR_ID} = ?"
        val selectionArgs = arrayOf(CR_ID.toString())
        return db.update(
            Participe.ParticipeTable.NAME,
            values,
            selection,
            selectionArgs)
    }

    fun getCoureurByTeamIdAndNumc(teamId: Int, numc: Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(Participe.ParticipeTable.CR_ID)

        val selection = "${Participe.ParticipeTable.E_ID} = ?"
        val selectionArgs = arrayOf(teamId.toString())

        val teamCursor = db.query(
            Participe.ParticipeTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        var rIds = emptyArray<Int>()
        while (teamCursor.moveToNext()) {
            val rId = teamCursor.getInt(teamCursor.getColumnIndex(Participe.ParticipeTable.CR_ID))
            rIds = rIds.plus(rId)
        }

        val runnerSelection = "${BaseColumns._ID} IN (?) AND ${Coureur.CoureurTable.NUMC} = ?"
        val runnerSelectionArgs = arrayOf(rIds.joinToString(", "), numc.toString())
        return db.query(
            Coureur.CoureurTable.NAME,
            null, // The array of columns to return (pass null to get all)
            runnerSelection, // The columns for the WHERE clause
            runnerSelectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )
    }
}