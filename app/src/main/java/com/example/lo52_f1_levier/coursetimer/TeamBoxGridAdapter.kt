package com.example.lo52_f1_levier.coursetimer

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lo52_f1_levier.DAO.CoureurDao
import com.example.lo52_f1_levier.DAO.EquipeDao
import com.example.lo52_f1_levier.DAO.ParticipeDao
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.model.Coureur
import com.example.lo52_f1_levier.model.Equipe
import com.example.lo52_f1_levier.model.Participe
import kotlinx.android.synthetic.main.team_box.view.*

/**
 * Link data to the RecyclerView listing the team boxes in the CourseTimerActivity
 */
class TeamBoxGridAdapter(private val context: Context,
                         private val courseId: Int,
                         private val goToDetails: (Int) -> Boolean,
                         private val getTimerValue: () -> Long,
                         private val isTimerStarted: () -> Boolean):
    RecyclerView.Adapter<TeamBoxGridAdapter.TeamBoxGridViewHolder>() {

    private val RUNNER_NAME_MAX_LENGTH = 15

    private val participeDao: ParticipeDao = ParticipeDao(context)
    private val runnerDao: CoureurDao = CoureurDao(context)
    private val teamDao: EquipeDao = EquipeDao(context)

    private var teams: Array<TeamBoxData> = emptyArray()

    init {
        fetchTeamsData()
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

    /**
     * Get the needed data of all the teams assigned to the course
     */
    private fun fetchTeamsData() {
        // get the runners of the course ordered by team
        val courseCursor = participeDao.getParticipeByC_ID(courseId)

        // check that course exists
        if (courseCursor === null || !courseCursor.moveToFirst()) {
            return
        }

        // indexes of relevant columns
        val teamIdIndex = courseCursor.getColumnIndex(Participe.ParticipeTable.E_ID)
        val runnerIdIndex = courseCursor.getColumnIndex(Participe.ParticipeTable.CR_ID)

        // info on the team being processed
        var currentTeam: Int = courseCursor.getInt(teamIdIndex)
        var currentTeamRunners: Array<String> = emptyArray()

        // turn every teams and runners data into relevant TeamBoxData
        do {
            val newTeam = courseCursor.getInt(teamIdIndex)

            // all runners of current team have been fetched
            if (currentTeam != newTeam) {
                // add team to the global list and prepare for the next one
                addTeamBoxData(currentTeam, currentTeamRunners)
                currentTeamRunners = emptyArray()
                currentTeam = newTeam
            }

            // add the current runner to the data of its team
            currentTeamRunners = fetchRunnerName(currentTeamRunners, courseCursor.getInt(runnerIdIndex))
        } while (courseCursor.moveToNext())

        // add the last team to the list
        addTeamBoxData(currentTeam, currentTeamRunners)
    }

    /**
     * Get the name of a given runner
     * and add it to the given list at the right position (depending on numc)
     * @return the new array
     */
    private fun fetchRunnerName(runners: Array<String>, runnerId: Int): Array<String> {
        val runnerCursor = runnerDao.getCoureurByID(runnerId)

        if (runnerCursor === null || !runnerCursor.moveToFirst()) {
            return addRunner(runners, "????", -1)
        }

        // get the relevant data of the runner
        val firstName = runnerCursor.getString(runnerCursor.getColumnIndex(Coureur.CoureurTable.CNAME))
        val lastName = runnerCursor.getString(runnerCursor.getColumnIndex(Coureur.CoureurTable.SURNAME))
        val position = runnerCursor.getInt(runnerCursor.getColumnIndex(Coureur.CoureurTable.NUMC))

        // test if full name fit in the team box
        if (("$firstName $lastName").length <= RUNNER_NAME_MAX_LENGTH) {
            return addRunner(runners, "$firstName $lastName", position)
        }

        // truncate the full name to make it fit
        return addRunner(runners, "${firstName[0]}. ${lastName.slice(0..12)}", position)
    }

    /**
     * Add a given runner name to a given list of runners at a given position
     * Increase the size of the array if necessary
     * @return the new array
     */
    private fun addRunner(runners: Array<String>, name: String, position: Int): Array<String> {
        if (position < 0) {
            return runners.plus(name)
        }

        // increase the size of the array just enough as to be able to fit the runner at the right pos
        var tmpRunners = runners.clone()
        if (position >= runners.size) {
            val tmp = (0..(position - runners.size)).toList().map { "????" }.toTypedArray()
            tmpRunners = runners.plus(tmp)
        }

        // add the runner to the list and return it
        tmpRunners[position] = name
        return tmpRunners
    }

    /**
     * Get and return the number of the team
     */
    private fun fetchTeamNumber(teamId: Int): Int {
        val teamCursor = teamDao.getEquipeByID(teamId)

        if (teamCursor === null || !teamCursor.moveToFirst()) {
            return -1
        }

        return teamCursor.getInt(teamCursor.getColumnIndex(Equipe.EquipeTable.ENUM))
    }

    /**
     * Turn ugly raw data into a beautiful TeamBoxData
     * and add it to the global list
     */
    private fun addTeamBoxData(teamId: Int, runners: Array<String>) {
        val teamNumber = fetchTeamNumber(teamId)
        teams = teams.plus(TeamBoxData(teamNumber, runners))
    }


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
