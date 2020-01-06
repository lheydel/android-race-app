package com.example.lo52_f1_levier.coursetimer

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.utils.GridAutofitLayoutManager
import com.example.lo52_f1_levier.view.DetailsCourseTimerFragment


class CourseTimerActivity : AppCompatActivity() {

    private lateinit var teamBoxGrid: RecyclerView
    private var courseId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_timer)

        courseId = intent.getIntExtra("courseId", 0)

        val columnWidth = resources.getDimension(R.dimen.teambox_width) + resources.getDimension(R.dimen.teambox_margin_side)

        teamBoxGrid = findViewById(R.id.teamBoxes)
        teamBoxGrid.layoutManager = GridAutofitLayoutManager(this, columnWidth.toInt())

        // TODO replace by real data
        val r1 = "Tim Yratraprapa"
        val r2 = "Jim Anvol"
        val r3 = "Malcolm Ansé"
        var teams = emptyArray<TeamBoxData>()
        for (i in 1..13) {
            teams = teams.plus(TeamBoxData(i, arrayOf(r1, r2, r3)))
        }
        teamBoxGrid.adapter = TeamBoxGridAdapter(teams, this::redirectToDetails)

        teamBoxGrid.setHasFixedSize(true)
    }

    private fun redirectToDetails(teamNumber: Int): Boolean {
        val intent = Intent(this, DetailsCourseTimerFragment::class.java)
        intent.putExtra("courseId", courseId)
        startActivity(intent)
        // Initialize a new instance of

        Log.d("tag",teamNumber.toString())
        // TODO send intent to go to details (courseId and teamNumber dispo) (example in CourseFragment, line 85)
        return true
    }
}
