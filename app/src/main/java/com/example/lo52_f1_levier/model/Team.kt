package com.example.lo52_f1_levier.model

/**
 * TODO
 *
 * @property id
 * @property name
 * @property number
 */
class Team (val id: Int,val name : String, val number : Int){
    /**
     * TODO
     *
     * @return
     */
    override fun toString() : String{
        return name + " " +id
    }
}