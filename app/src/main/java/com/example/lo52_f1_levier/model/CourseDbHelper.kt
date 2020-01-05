package com.example.lo52_f1_levier.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CourseDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_COURSE)
        db.execSQL(SQL_CREATE_COUREUR)
        db.execSQL(SQL_CREATE_EQUIPE)
        db.execSQL(SQL_CREATE_PARTICIPE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_COURSE)
        db.execSQL(SQL_DELETE_COUREUR)
        db.execSQL(SQL_DELETE_EQUIPE)
        db.execSQL(SQL_DELETE_PARTICIPE)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "Course.db"
    }
}