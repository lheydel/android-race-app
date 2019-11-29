package com.example.lo52_f1_levier.model

import android.provider.BaseColumns

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
    }
}
object Participe{
object ParticipeTable : BaseColumns {
    const val NAME = "Participe"
    const val TITLE = "title"
    const val NUMC = "Numc"
    const val ENAME = "Ename"
    const val TIME1 = "Time1"
    const val TIME2 = "Time2"
    const val TIME3 = "Time3"
    //TODO : add times
}}

