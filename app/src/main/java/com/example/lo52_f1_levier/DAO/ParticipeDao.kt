package com.example.lo52_f1_levier.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.lo52_f1_levier.model.Coureur
import com.example.lo52_f1_levier.model.Participe
import com.example.lo52_f1_levier.model.CourseDbHelper
import com.example.lo52_f1_levier.model.Equipe

/**
 * ensemble des fonction permettant de modifier le contenue de la table participe
 */
class ParticipeDao(context : Context) {
    val dbHelper = CourseDbHelper(context)

    /**
     * Permet l'insertion dans la table participe
     */
    fun insertParticipe(C_ID: Int, CR_ID: Int, E_ID : Int): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Participe.ParticipeTable.COUREURID, CR_ID )
            put(Participe.ParticipeTable.COURSE_ID, C_ID)
            put(Participe.ParticipeTable.EQUIPE_ID, E_ID)
        }
        return db?.insert(Participe.ParticipeTable.NAME, null, values)
    }

    /**
     * Renvoie les enregistrements dont l'id du coureur = COUREURID
     */
    fun getParticipeByRunnerId(CR_ID:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.COUREURID, Participe.ParticipeTable.COURSE_ID,
            Participe.ParticipeTable.EQUIPE_ID,
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

        val selection = "${Participe.ParticipeTable.COUREURID} = ?"
        val selectionArgs = arrayOf(CR_ID.toString())

        val sortOrder = "${Participe.ParticipeTable.EQUIPE_ID} DESC"

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
     */
    fun getParticipeByID(ID:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.COUREURID, Participe.ParticipeTable.COURSE_ID,
            Participe.ParticipeTable.EQUIPE_ID,
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

        val sortOrder = "${Participe.ParticipeTable.EQUIPE_ID} DESC"

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
     *Renvoie les enregistrements dont l'id de l'équipe  = EQUIPE_ID
     */
    fun getParticipeByE_ID(E_ID:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.COUREURID, Participe.ParticipeTable.COURSE_ID,
            Participe.ParticipeTable.EQUIPE_ID,
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

        val selection = "${Participe.ParticipeTable.EQUIPE_ID} = ?"
        val selectionArgs = arrayOf(E_ID.toString())

        val sortOrder = "${Participe.ParticipeTable.COUREURID} DESC"

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
     * Renvoie les enregistrements dont l'id de la course  = COURSE_ID
     */
    fun getParticipeByC_ID(C_ID:Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.COUREURID, Participe.ParticipeTable.COURSE_ID,
            Participe.ParticipeTable.EQUIPE_ID,
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

        val selection = "${Participe.ParticipeTable.COURSE_ID} = ?"
        val selectionArgs = arrayOf(C_ID.toString())

        val sortOrder = Participe.ParticipeTable.EQUIPE_ID  // important for TeamBoxGridAdapter

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
     * Renvoie les enregistrements dont l'id de la course  = COURSE_ID et l'id de l'équipe =EQUIPE_ID
     */
    fun getParticipeByC_ID_E_ID(C_ID:Int,E_ID: Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(
            BaseColumns._ID, Participe.ParticipeTable.COUREURID, Participe.ParticipeTable.COURSE_ID,
            Participe.ParticipeTable.EQUIPE_ID,
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

        val selection = "${Participe.ParticipeTable.COURSE_ID} = ? AND ${Participe.ParticipeTable.EQUIPE_ID} = ?"
        val selectionArgs = arrayOf(C_ID.toString(), E_ID.toString())

        val sortOrder = "${Participe.ParticipeTable.COUREURID} DESC"

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

    fun getTeamsOfCourse(courseId: Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(Participe.ParticipeTable.EQUIPE_ID)

        val selection = "${Participe.ParticipeTable.COURSE_ID} = ?"
        val selectionArgs = arrayOf(courseId.toString())

        return db.query(
            Participe.ParticipeTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )
    }

    /**
     * Supprime les enregistrements dont l'id du coureur = COUREURID
     */
    fun deleteParticipeByCR_ID(CR_ID: Int): Int {
        val db = dbHelper.writableDatabase
        val selection = "${Participe.ParticipeTable.COUREURID} = ?"
        val selectionArgs = arrayOf(CR_ID.toString())
        val deletedRows = db.delete(Participe.ParticipeTable.NAME, selection, selectionArgs)
        return deletedRows
    }

    /**
     * deleteParticipeByCourseIdAndTeamId est utilisé pour supprimer des enregistrement dans la base
     * participe en focntion de l'id de la course et l'id de l'équipe
     */
    fun deleteParticipeByCourseIdAndTeamId(C_ID: Int, E_ID: Int): Int {
        val db = dbHelper.writableDatabase
        val selection = "${Participe.ParticipeTable.COURSE_ID} = ? AND ${Participe.ParticipeTable.EQUIPE_ID} = ?"
        val selectionArgs = arrayOf(C_ID.toString(),E_ID.toString())
        val deletedRows = db.delete(Participe.ParticipeTable.NAME, selection, selectionArgs)
        return deletedRows
    }

    /**
     * setTimeByRunnerId permet de definir le temps numTime du coureur COUREURID  a la valeur time
     */
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

        val selection = "${Participe.ParticipeTable.COUREURID} = ?"
        val selectionArgs = arrayOf(CR_ID.toString())
        return db.update(
            Participe.ParticipeTable.NAME,
            values,
            selection,
            selectionArgs)
    }

    /**
     * Get a runner by its team and position in the team
     */
    fun getCoureurByTeamIdAndNumc(teamId: Int, numc: Int): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(Participe.ParticipeTable.COUREURID)

        val selection = "${Participe.ParticipeTable.EQUIPE_ID} = ?"
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
            val rId = teamCursor.getInt(teamCursor.getColumnIndex(Participe.ParticipeTable.COUREURID))
            rIds = rIds.plus(rId)
        }
        teamCursor.close()

        val queryIdsParameter = rIds.map { "?" }.joinToString(", ", "(", ")")
        val runnerSelection = "${BaseColumns._ID} IN $queryIdsParameter AND ${Coureur.CoureurTable.NUMC} = ?"
        val runnerSelectionArgs = rIds.map{ id -> id.toString() }.plus(numc.toString()).toTypedArray()
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

    fun nextTeamPosition(courseId: Int): Int {
        val db = dbHelper.readableDatabase
        val teamListCursor = getTeamsOfCourse(courseId)

        if (teamListCursor === null || !teamListCursor.moveToFirst()) {
            return 1
        }

        var teamIds = emptyArray<Int>()
        while (teamListCursor.moveToNext()) {
            val tId = teamListCursor.getInt(teamListCursor.getColumnIndex(Participe.ParticipeTable.EQUIPE_ID))
            teamIds = teamIds.plus(tId)
        }

        val projection = arrayOf("MAX(${Equipe.EquipeTable.POSITION})")
        val selection = "${BaseColumns._ID} IN (?)"
        val selectionArgs = arrayOf(teamIds.joinToString(", "))
        val posCursor = db.query(
            Equipe.EquipeTable.NAME,
            projection, // The array of columns to return (pass null to get all)
            selection, // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        posCursor.moveToFirst()
        return posCursor.getInt(0)
    }
}