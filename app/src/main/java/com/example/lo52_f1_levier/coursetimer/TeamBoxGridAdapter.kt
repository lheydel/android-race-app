package com.example.lo52_f1_levier.coursetimer

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.lo52_f1_levier.R
import kotlinx.android.synthetic.main.team_box.view.*

/**
 * Link data to the RecyclerView listing the team boxes in the CourseTimerActivity
 */
class TeamBoxGridAdapter(private val teams: Array<TeamBoxData>) :
    RecyclerView.Adapter<TeamBoxGridAdapter.TeamBoxGridViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class TeamBoxGridViewHolder(private val teamBox: View) : RecyclerView.ViewHolder(teamBox) {
        fun setData(team: TeamBoxData) {
            teamBox.teamNumber.text = team.teamNumber.toString()
            teamBox.progressBar.max = team.NB_TOTAL_STEPS - 1
            updateData(team)

            teamBox.setOnClickListener { nextStep(team) }
        }

        private fun updateData(team: TeamBoxData) {
            if (team.isOver) {
                displayFinish()
                return
            }

            teamBox.runnerName.text = team.getCurrentRunner()

            teamBox.imgJersey.setImageResource(team.getDrawableRunner())
            teamBox.imgPassage.setImageResource(team.getDrawablePassage())
            teamBox.imgStep.setImageResource(team.getDrawableStep())

            teamBox.progressBar.progress = team.totalStepsDone
        }

        private fun nextStep(team: TeamBoxData) {
            team.incrementStep()
            updateData(team)
        }

        private fun displayFinish() {
            teamBox.runnerName.text = "Parcours Terminé"
            teamBox.runningState.visibility = View.INVISIBLE
            teamBox.imgJersey.visibility = View.GONE
            teamBox.imgFinish.visibility = View.VISIBLE
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamBoxGridViewHolder {
        // create a new view
        val teamBox: View = LayoutInflater.from(parent.context)
                                          .inflate(R.layout.team_box, parent, false)

        // set the view's size, margins, paddings and layout parameters
        return TeamBoxGridViewHolder(teamBox)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: TeamBoxGridViewHolder, position: Int) {
        viewHolder.setData(teams[position])
    }

    // Return the size of the dataset (invoked by the layout manager)
    override fun getItemCount() = teams.size
}
