package com.example.lo52_f1_levier.model

class Run (val id: Int,val name : String, val date : String){



    override fun toString() : String{
        return name + " : " + date
    }
}