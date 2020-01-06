package com.example.lo52_f1_levier.coursetimer

import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lo52_f1_levier.R
import kotlinx.android.synthetic.main.team_box.view.*

/**
 * Link data to the RecyclerView listing the team boxes in the CourseTimerActivity
 */
class TeamBoxGridAdapter(private val teams: Array<TeamBoxData>,
                         private val goToDetails: (Int) -> Boolean,
                         private val getTimerValue: () -> Long,
                         private val isTimerStarted: () -> Boolean):
    RecyclerView.Adapter<TeamBoxGridAdapter.TeamBoxGridViewHolder>() {

//    private val teams: Array<TeamBoxData>

    init {
//        teams = emptyArray()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamBoxGridViewHolder {
        val teamBox: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_box, parent, false)
        return TeamBoxGridViewHolder(teamBox, goToDetails, isTimerStarted)
    }

    override fun onBindViewHolder(viewHolder: TeamBoxGridViewHolder, position: Int) {
        viewHolder.setData(teams[position])
    }

    override fun getItemCount() = teams.size

    class TeamBoxGridViewHolder(private val teambox: View,
                                private val goToDetails: (Int) -> Boolean,
                                private val isTimerStarted: () -> Boolean) :
        RecyclerView.ViewHolder(teambox), View.OnCreateContextMenuListener {

        init {
            teambox.setOnCreateContextMenuListener(this)
        }

        /**
         * Link the team box with a given team
         */
        fun setData(team: TeamBoxData) {
            teambox.teamNumber.text = team.teamNumber.toString()
            teambox.progressBar.max = team.NB_TOTAL_STEPS - 1
            updateData(team)

            teambox.setOnClickListener {
                if (isTimerStarted() && !team.isOver) {
                    nextStep(team)
                }
            }
        }

        /**
         * Update the team box with the new team data
         */
        private fun updateData(team: TeamBoxData) {
            if (team.isOver) {
                displayFinish()
                return
            }

            teambox.runnerName.text = team.getCurrentRunner()

            teambox.imgJersey.setImageResource(team.getDrawableRunner())
            teambox.imgPassage.setImageResource(team.getDrawablePassage())
            teambox.imgStep.setImageResource(team.getDrawableStep())

            teambox.progressBar.progress = team.totalStepsDone
        }

        /**
         * Save the time and display the next step of the course for the team
         */
        private fun nextStep(team: TeamBoxData) {
            // TODO save time
            team.incrementStep()
            updateData(team)
        }

        /**
         * Display the final state of the team box
         */
        private fun displayFinish() {
            teambox.runnerName.text = "Parcours Terminé"
            teambox.runningState.visibility = View.INVISIBLE
            teambox.imgJersey.visibility = View.GONE
            teambox.imgFinish.visibility = View.VISIBLE
        }

        override fun onCreateContextMenu(menu: ContextMenu, teamBox: View, menuInfo: ContextMenu.ContextMenuInfo?) {
            menu.add(teambox.teamNumber.text.toString().toInt(), 0, 0, "Détails")
                .setOnMenuItemClickListener { goToDetails(teamBox.teamNumber.text.toString().toInt()) }
        }
    }
}
