package com.example.lo52_f1_levier.coursetimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.utils.GridAutofitLayoutManager


class CourseTimerActivity : AppCompatActivity() {

    private lateinit var teamBoxGrid: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_timer)

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
        teamBoxGrid.adapter = TeamBoxGridAdapter(teams)

        teamBoxGrid.setHasFixedSize(true)
        //registerForContextMenu(teamBoxGrid)
    }

    /*override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.teambox_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.teambox_menu_detail -> {
                Toast.makeText(applicationContext, "blblbl", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }*/
}
