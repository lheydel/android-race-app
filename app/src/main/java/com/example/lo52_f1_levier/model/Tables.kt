package com.example.lo52_f1_levier.model

import android.provider.BaseColumns
/*
* Scripte de création des tables
*
* */
const val SQL_CREATE_COURSE =
    "CREATE TABLE ${Course.Coursetable.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${Course.Coursetable.TITLE} TEXT," +
            "${Course.Coursetable.DATE} TEXT)"

const val SQL_DELETE_COURSE = "DROP TABLE IF EXISTS ${Course.Coursetable.TABLE_NAME}"

const val SQL_CREATE_EQUIPE =
    "CREATE TABLE ${Equipe.EquipeTable.NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${Equipe.EquipeTable.ENAME} TEXT," +
            "${Equipe.EquipeTable.ENUM} NUMBER)"

const val SQL_DELETE_EQUIPE = "DROP TABLE IF EXISTS ${Equipe.EquipeTable.NAME}"

const val SQL_CREATE_COUREUR =
    "CREATE TABLE ${Coureur.CoureurTable.NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${Coureur.CoureurTable.NUMC} NUMBER,"+
            "${Coureur.CoureurTable.CNAME} TEXT," +
            "${Coureur.CoureurTable.SURNAME} TEXT)"

const val SQL_DELETE_COUREUR = "DROP TABLE IF EXISTS ${Coureur.CoureurTable.NAME}"

const val SQL_CREATE_PARTICIPE =
    "CREATE TABLE ${Participe.ParticipeTable.NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${Participe.ParticipeTable.TITLE} TEXT," +
            "${Participe.ParticipeTable.NUMC} NUMBER,"+
            "${Participe.ParticipeTable.ENAME} TEXT,"+
            "${Participe.ParticipeTable.TIME1} NUMBER,"+
            "${Participe.ParticipeTable.TIME2} NUMBER,"+
            "${Participe.ParticipeTable.TIME3} NUMBER,"+
            "${Participe.ParticipeTable.TIME4} NUMBER,"+
            "${Participe.ParticipeTable.TIME5} NUMBER,"+
            "${Participe.ParticipeTable.TIME6} NUMBER,"+
            "${Participe.ParticipeTable.TIME7} NUMBER,"+
            "${Participe.ParticipeTable.TIME8} NUMBER,"+
            "${Participe.ParticipeTable.TIME9} NUMBER,"+
            "${Participe.ParticipeTable.TIME10} NUMBER)"


const val SQL_DELETE_PARTICIPE = "DROP TABLE IF EXISTS ${Participe.ParticipeTable.NAME}"