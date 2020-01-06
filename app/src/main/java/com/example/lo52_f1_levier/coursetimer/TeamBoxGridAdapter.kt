package com.example.lo52_f1_levier.coursetimer

import android.content.Intent
import android.view.*
import android.widget.AdapterView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.view.TabDetailActivity
import com.example.lo52_f1_levier.view.TabDetailFragment
import kotlinx.android.synthetic.main.team_box.view.*

/**
 * Link data to the RecyclerView listing the team boxes in the CourseTimerActivity
 */
class TeamBoxGridAdapter(private val teams: Array<TeamBoxData>, private val goToDetails: (Int) -> Boolean) : RecyclerView.Adapter<TeamBoxGridAdapter.TeamBoxGridViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class TeamBoxGridViewHolder(private val teambox: View, private val goToDetails: (Int) -> Boolean) :
        RecyclerView.ViewHolder(teambox), View.OnCreateContextMenuListener {

        init {
            teambox.setOnCreateContextMenuListener(this)
        }

        fun setData(team: TeamBoxData) {
            teambox.teamNumber.text = team.teamNumber.toString()
            teambox.progressBar.max = team.NB_TOTAL_STEPS - 1
            updateData(team)

            teambox.setOnClickListener { nextStep(team) }
        }

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

        private fun nextStep(team: TeamBoxData) {
            team.incrementStep()
            updateData(team)
        }

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

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamBoxGridViewHolder {
        // create a new view
        val teamBox: View = LayoutInflater.from(parent.context)
                                          .inflate(R.layout.team_box, parent, false)

        // set the view's size, margins, paddings and layout parameters
        return TeamBoxGridViewHolder(teamBox, goToDetails)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: TeamBoxGridViewHolder, position: Int) {
        viewHolder.setData(teams[position])
    }

    // Return the size of the dataset (invoked by the layout manager)
    override fun getItemCount() = teams.size
}
