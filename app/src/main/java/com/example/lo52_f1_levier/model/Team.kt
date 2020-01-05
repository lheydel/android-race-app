package com.example.lo52_f1_levier.model

class Team (val id: Int,val name : String){

    override fun toString() : String{
        return name + " " +id
    }
}