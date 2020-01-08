package com.example.lo52_f1_levier.model

/**
 * TODO
 *
 * @property id
 * @property name
 * @property date
 */
class Run (val id: Int,val name : String, val date : String){


    /**
     * TODO
     *
     * @return
     */
    override fun toString() : String{
        return name + " : " + date
    }
}