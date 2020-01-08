package com.example.lo52_f1_levier.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import com.example.lo52_f1_levier.DAO.ParticipeDao
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.model.Coureur
import com.example.lo52_f1_levier.model.Participe
import com.example.lo52_f1_levier.model.Runner
import kotlinx.android.synthetic.main.activity_details_course_timer.*

class DetailsCourseTimerFragment : AppCompatActivity() {

    private var courseId: Int = -1
    private var teamId: Int = -1
    private lateinit var participeDao: ParticipeDao

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_course_timer)

        courseId = intent.getIntExtra("courseId",0)
        teamId = intent.getIntExtra("teamId", 0)

        participeDao = ParticipeDao(this.baseContext)

        // Get the different runners

        // The cursor to iterate through the DB
        val cursor = participeDao.getParticipeByE_ID(teamId)

        // The list contanining the runners
        val runners = ArrayList<Runner>()
        // Let's iterate
        with(cursor!!){
            // While we still have something in our cursor
            while (moveToNext())
            {
                // We create an instance of a runner
                val runner = Runner(getInt(getColumnIndexOrThrow(Coureur.CoureurTable.NUMC)),
                    getString(getColumnIndexOrThrow(Coureur.CoureurTable.CNAME)),
                    getString(getColumnIndexOrThrow(Coureur.CoureurTable.SURNAME)))
                // We add the runner to our list
                runners.add(runner)
            }
        }
        // We close the cursor
        cursor.close()

        // for each of them, create a new "timer"
        runners.forEach{
            var view = LayoutInflater.from(this).inflate(R.layout.fragment_progression,null,false)
            val cRunner = participeDao.getParticipeByC_ID(it.numc)
            var t1 =""
            with(cRunner!!){
                while(moveToNext())
                {
                    t1 = getString(getColumnIndexOrThrow(Participe.ParticipeTable.TIME1)) // do the same for every time
                }
            }

            // set the progression according to the times
            if(t1 !== "" )
            {
                Log.d("Tag",t1)
            }

            verticalLayout.addView(view)
        }

    }




}

/*
*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.LayoutInflater
import android.view.View
import com.example.lo52_f1_levier.R
import kotlinx.android.synthetic.main.fragment_progression.view.*


import kotlinx.android.synthetic.main.seekbar.view.*


class MainActivity : AppCompatActivity()
{


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        for(i in 1..4)
        {
            var view = LayoutInflater.from(this).inflate(R.layout.seekbar,null,false)
            val b = view.button
            b.setOnClickListener {
                buttonClicked(view)
            }

            verticalLayout.addView(view)
            //verticalLayout.addView(LayoutInflater.from(applicationContext).inflate(R.layout.seekbar,verticalLayout,false))
            //view = LayoutInflater.from(applicationContext).inflate(R.layout.seekbar,verticalLayout,true)
        }
    }

    private fun buttonClicked(v : View)
    {
        val rdm = (1..5).shuffled().first()
        v.seekBar.progress = rdm
        when (rdm)
        {
            1 ->
            {
                v.Sprint.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Sprint.isVisible=true

                v.Obstacle.visibility=View.INVISIBLE

                v.PitStop.visibility=View.INVISIBLE

                v.Sprint2.visibility=View.INVISIBLE

                v.Obstacle2.visibility=View.INVISIBLE
            }
            2 ->
            {
                v.Sprint.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Sprint.isVisible=true

                v.Obstacle.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Obstacle.isVisible=true

                v.PitStop.visibility=View.INVISIBLE

                v.Sprint2.visibility=View.INVISIBLE

                v.Obstacle2.visibility=View.INVISIBLE
            }
            3 ->
            {
                v.Sprint.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Sprint.isVisible=true

                v.Obstacle.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Obstacle.isVisible=true

                v.PitStop.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.PitStop.isVisible=true

                v.Sprint2.visibility=View.INVISIBLE

                v.Obstacle2.visibility=View.INVISIBLE
            }
            4 ->
            {
                v.Sprint.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Sprint.isVisible=true

                v.Obstacle.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Obstacle.isVisible=true

                v.PitStop.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.PitStop.isVisible=true

                v.Sprint2.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Sprint2.isVisible=true

                v.Obstacle2.visibility=View.INVISIBLE
            }
            5 ->
            {
                v.Sprint.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Sprint.isVisible=true

                v.Obstacle.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Obstacle.isVisible=true

                v.PitStop.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.PitStop.isVisible=true

                v.Sprint2.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Sprint2.isVisible=true

                v.Obstacle2.text="${(0..2).shuffled().first()}' ${(10..59).shuffled().first()}\""
                v.Obstacle2.isVisible=true
            }
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }
    }


}*/