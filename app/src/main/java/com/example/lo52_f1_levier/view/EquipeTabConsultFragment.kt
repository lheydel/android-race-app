package com.example.lo52_f1_levier.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lo52_f1_levier.DAO.CourseDao
import com.example.lo52_f1_levier.DAO.EquipeDao
import com.example.lo52_f1_levier.DAO.ParticipeDao
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.model.Equipe
import com.example.lo52_f1_levier.model.Run
import com.example.lo52_f1_levier.model.Team
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_equipe_tab_consult.*
import kotlinx.android.synthetic.main.fragment_equipe_tab_consult.courseSelector
import kotlinx.android.synthetic.main.fragment_equipe_tab_consult.editTextNbTeam
import kotlinx.android.synthetic.main.fragment_equipe_tab_consult.listTeam
import kotlinx.android.synthetic.main.tab_list_content.*
import kotlinx.android.synthetic.main.fragment_equipe.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
typealias TeamClickListener = (View, Team) -> Unit

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [EquipeTabConsultFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EquipeTabConsultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EquipeTabConsultFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var courseDao: CourseDao
    private lateinit var equipeDao: EquipeDao
    private lateinit var participeDao: ParticipeDao
    private lateinit var teamAdapter : TeamAdapter
    private lateinit var selectedTeam : Team
    private lateinit var course : Run

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_equipe_tab_consult, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        equipeDao = EquipeDao(this.context!!)
        courseDao = CourseDao(this.context!!)
        participeDao = ParticipeDao(this.context!!)

        add_team.setOnClickListener {
            this.parentFragment?.tab_layout?.getTabAt(0)?.select()
        }

        edit_team.setOnClickListener {
            if(::selectedTeam.isInitialized) {
                val intent = Intent(this.context, TeamEditActivity::class.java)
                intent
                    .putExtra("teamId", selectedTeam.id)
                    .putExtra("courseId", course.id)
                startActivityForResult(intent, 10001)
            }else
                Toast.makeText(this.context, "Vous devez sélectionner un Participant", Toast.LENGTH_SHORT).show()
        }

        delete_team.setOnClickListener {
            if(::selectedTeam.isInitialized){

                participeDao.deleteParticipeByCourseIdAndTeamId(course.id,selectedTeam.id)
                equipeDao.deleteEquipe(selectedTeam.id.toString())

                val ft = fragmentManager!!.beginTransaction()
                if (Build.VERSION.SDK_INT >= 26) {
                    ft.setReorderingAllowed(false)
                }
                ft.detach(this).attach(this).commit()
            }
            else
                Toast.makeText(this.context, "Vous devez sélectionner une Equipe", Toast.LENGTH_SHORT).show()
        }

        val teams = ArrayList<Team>()
        val courseCursor = courseDao.getAllCourse()
        val courses = ArrayList<Run>()

        with(courseCursor!!){
            while (moveToNext()){
                val run = Run(
                    getInt(getColumnIndexOrThrow(android.provider.BaseColumns._ID)),
                    getString(getColumnIndexOrThrow(com.example.lo52_f1_levier.model.Course.Coursetable.TITLE)),
                    getString(getColumnIndexOrThrow(com.example.lo52_f1_levier.model.Course.Coursetable.DATE))
                )
                courses.add(run)
            }
        }

        teamAdapter = TeamAdapter(
            onClickListener = this::selectTeam
        )
        teamAdapter.replaceItems(teams)
        listTeam.layoutManager = LinearLayoutManager(this.context)
        listTeam.adapter = teamAdapter

        val spinnerAdapter = ArrayAdapter<Run>(this.context!!,
            android.R.layout.simple_spinner_item, courses)
        courseSelector.adapter = spinnerAdapter

        courseSelector?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                course = courseSelector.selectedItem as Run

                val teamInCourseCursor = equipeDao.getTeamForACourse(course.id)
                with(teamInCourseCursor!!){
                    teams.clear()
                    while (moveToNext()){
                        val team = Team(
                            getInt(getColumnIndexOrThrow(android.provider.BaseColumns._ID)),
                            getString(getColumnIndexOrThrow(Equipe.EquipeTable.ENAME)),
                            getInt(getColumnIndexOrThrow(Equipe.EquipeTable.ENUM))
                        )
                        teams.add(team)


                    }
                    Log.d("TESTSTSTSTSTST", teams.toString())
                }
                editTextNbTeam.setText(teams.size.toString())
                setTeam(teams)
                Log.d("TESTSTSTSTSTST", teamAdapter.itemCount.toString())
            }

        }

    }

    private fun setTeam(team : ArrayList<Team>){
        teamAdapter.replaceItems(team)
    }

    private fun selectTeam(view: View,team: Team) {
        selectedTeam = team
        editTextSelectedTeam.setText("Equipe " + selectedTeam.number + " : " + selectedTeam.name)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10001 && resultCode == Activity.RESULT_OK) {
            // recreate your fragment here
            fragmentManager!!
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commit()
        }
    }

    class TeamAdapter(private val onClickListener: TeamClickListener) : RecyclerView.Adapter<TeamAdapter.ViewHolder>(){
        private var equipes = ArrayList<Team>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.tab_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val team = equipes[position]
            holder.content.text = "Equipe " + team.number + " : " + team.name
            holder.content.setOnClickListener { view ->
                onClickListener(view, team)
            }
        }

        fun replaceItems(team: ArrayList<Team>) {
            this.equipes = team
            notifyDataSetChanged()
        }

        fun addItem(team: Team){
            this.equipes.add(team)
            notifyDataSetChanged()
        }

        fun removeItem(team: Team){
            this.equipes.remove(team)
            notifyDataSetChanged()

        }

        override fun getItemCount(): Int {
            return equipes.size
        }

        inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EquipeTabConsultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EquipeTabConsultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
