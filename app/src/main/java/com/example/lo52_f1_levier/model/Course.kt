package com.example.lo52_f1_levier.model

import android.provider.BaseColumns
/**
 *
 *Objects contenants les noms des champs des tables
 *
 * @author GMuller
 *
 * */
object Course {
    object Coursetable : BaseColumns {
        const val TABLE_NAME = "Course"
        const val TITLE = "title"
        const val DATE = "date"
    }
}



object Coureur{
    object CoureurTable : BaseColumns {
        const val NAME = "Coureur"
        const val NUMC = "Numc"
        const val CNAME = "Cname"
        const val SURNAME = "Surname"
    }
}
object Equipe{
    object EquipeTable : BaseColumns {
        const val NAME = "Equipe"
        const val ENAME = "Ename"
        const val ENUM = "Enum"
    }
}
object Participe{
object ParticipeTable : BaseColumns {
    const val NAME = "Participe"
    const val C_ID = "C_ID" // Course
    const val CR_ID = "CR_ID" // Coureur

    const val E_ID = "E_ID"
    const val TIME1 = "Time1"
    const val TIME2 = "Time2"
    const val TIME3 = "Time3"
    const val TIME4 = "Time4"
    const val TIME5 = "Time5"
    const val TIME6 = "Time6"
    const val TIME7 = "Time7"
    const val TIME8 = "Time8"
    const val TIME9 = "Time9"
    const val TIME10 = "Time10"
    //TODO : add times plus de times ????
}}

