package com.example.lo52_f1_levier.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lo52_f1_levier.DAO.CoureurDao
import com.example.lo52_f1_levier.DAO.CourseDao
import com.example.lo52_f1_levier.DAO.EquipeDao
import com.example.lo52_f1_levier.DAO.ParticipeDao
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.model.Coureur
import com.example.lo52_f1_levier.model.Run
import com.example.lo52_f1_levier.model.Runner
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_equipe_tab_ajouter.*
import kotlinx.android.synthetic.main.tab_list_content.content
import kotlinx.android.synthetic.main.team_member_list_content.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
typealias AvailableRunnerClickListener = (View, Runner) -> Unit
typealias TeamRunnerClickListener = (View, Runner) -> Unit

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [EquipeTabAjouterFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EquipeTabAjouterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EquipeTabAjouterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var coureurDao : CoureurDao
    private lateinit var equipeDao : EquipeDao
    private lateinit var participeDao: ParticipeDao
    private lateinit var courseDao: CourseDao
    private lateinit var selectedRunner : Runner
    private lateinit var selectedMember : Runner
    private lateinit var adapter: RunnerAdapter
    private lateinit var teamAdapter: TeamMemberAdapter
    private lateinit var course: Run

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
        return inflater.inflate(R.layout.fragment_equipe_tab_ajouter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        equipeDao = EquipeDao(this.context!!)

        save.setOnClickListener{
            if(!edt_teamName.text.isEmpty()) {

                if (teamAdapter.getItemCount() == 3 /*&& teamAdapter.getItemCount() >=1*/) {
                    equipeDao = EquipeDao(this.context!!)
                    participeDao = ParticipeDao(this.context!!)
                    val equipeRowId = equipeDao.insertEquipe(edt_teamName.text.toString(),edt_teamNumber.text.toString().toInt())
                    participeDao.insertParticipe(course.id, teamAdapter.getItem(0).id, equipeRowId!!.toInt())
                    participeDao.insertParticipe(course.id, teamAdapter.getItem(1).id, equipeRowId!!.toInt())
                    participeDao.insertParticipe(course.id, teamAdapter.getItem(2).id, equipeRowId!!.toInt())

                    edt_teamName.text.clear()
                }
                else{
                    Toast.makeText(this.context, "L'équipe doit avoir 3 memebres", Toast.LENGTH_SHORT).show()
                }
            }
            else
                Toast.makeText(this.context, "Veuiller renseigner le nom de l'équipe", Toast.LENGTH_SHORT).show()
        }

        btn_left.setOnClickListener{
            if(::selectedMember.isInitialized){
                if(selectedMember.isTeamMember){
                    selectedMember.isTeamMember = false
                    adapter.addItem(selectedMember)
                    teamAdapter.removeItem(selectedMember)


                    edt_selectedMember.text.clear()
                }
                else
                    Toast.makeText(this.context, "Vous devez sélectionner un membre de l'équipe", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this.context, "Vous devez sélectionner un membre de l'équipe", Toast.LENGTH_SHORT).show()
        }

        btn_right.setOnClickListener{
            if(::selectedRunner.isInitialized){
                if(!selectedRunner.isTeamMember){
                    selectedRunner.isTeamMember = true
                    if(teamAdapter.addItem(selectedRunner))
                        adapter.removeItem(selectedRunner)
                    else{
                        selectedRunner.isTeamMember = false
                        teamMaxSize()
                    }
                    edt_selectedParticipant.text.clear()
                }
                else
                    Toast.makeText(this.context, "Vous devez sélectionner un Participant", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this.context, "Vous devez sélectionner un Participant", Toast.LENGTH_SHORT).show()
        }

        btn_up.setOnClickListener{
            if(::selectedMember.isInitialized){
                if(selectedMember.isTeamMember){
                    if(teamAdapter.getItemPos(selectedMember) > 0)
                        teamAdapter.moveUp(selectedMember)
                }
                else
                    Toast.makeText(this.context, "Vous devez sélectionner un membre de l'équipe", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this.context, "Vous devez sélectionner un membre de l'équipe", Toast.LENGTH_SHORT).show()
        }

        btn_down.setOnClickListener{
            if(::selectedMember.isInitialized){
                if(selectedMember.isTeamMember){
                    if(teamAdapter.getItemPos(selectedMember) < 2)
                        teamAdapter.moveDown(selectedMember)
                }
                else
                    Toast.makeText(this.context, "Vous devez sélectionner un membre de l'équipe", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this.context, "Vous devez sélectionner un membre de l'équipe", Toast.LENGTH_SHORT).show()
        }

        coureurDao = CoureurDao(this.context!!)

        val coureurs = ArrayList<Runner>()

        adapter = RunnerAdapter(
            onClickListener = this::selectItem
        )
        adapter.replaceItems(coureurs)
        listFreeParticipant.layoutManager = LinearLayoutManager(this.context)
        listFreeParticipant.adapter = adapter

        teamAdapter = TeamMemberAdapter(this::selectMember, context)
        listTeamMembers.layoutManager = LinearLayoutManager(this.context)
        listTeamMembers.adapter = teamAdapter

        courseDao = CourseDao(this.context!!)
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
        if (courses.isEmpty()) {
            val alert = AlertDialog.Builder(this.context!!)
            alert.setTitle("Aucune course de disponible")
            alert.setMessage("Pour pouvoir créer un équipe vous devez avoir au moins une courses de disponible")
            alert.show()
        }
        else{
            val spinnerAdapter = ArrayAdapter<Run>(this.context!!,
                android.R.layout.simple_spinner_item, courses)
            courseSelector.adapter = spinnerAdapter
        }

        courseSelector?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                course = courseSelector.selectedItem as Run


                val freeRunnerCursor = coureurDao.getCoureurFree(course.id)

                with(freeRunnerCursor!!){
                    coureurs.clear()
                    while (moveToNext()){
                        val freeRunner = Runner(
                            getInt(getColumnIndexOrThrow(BaseColumns._ID)),
                            getInt(getColumnIndexOrThrow(Coureur.CoureurTable.NUMC)),
                            getString(getColumnIndexOrThrow(Coureur.CoureurTable.CNAME)),
                            getString(getColumnIndexOrThrow(Coureur.CoureurTable.SURNAME)))
                        coureurs.add(freeRunner)
                    }
                }
                val teamsCursor = equipeDao.getTeamForACourse(course.id)
                var nb = 0
                with(teamsCursor!!){
                    while (moveToNext()){
                        nb ++
                    }
                }

                nb ++
                edt_teamNumber.setText(nb.toString())

                setFreeRunner(coureurs)

            }

        }

    }

    private fun setFreeRunner(runners : ArrayList<Runner>){
        adapter.replaceItems(runners)
    }

    private fun selectItem(view: View,runner: Runner) {
        selectedRunner = runner
        edt_selectedParticipant.setText(selectedRunner.cname + " " + selectedRunner.surname)
    }

    private fun selectMember(view: View,runner: Runner){
        selectedMember = runner
        edt_selectedMember.setText(selectedMember.cname + " " + selectedMember.surname)
    }

    fun teamMaxSize(){
        Toast.makeText(this.context, "Taille maximum de l'équipe atteinte", Toast.LENGTH_SHORT).show()
    }

    class RunnerAdapter(private val onClickListener: AvailableRunnerClickListener) : RecyclerView.Adapter<RunnerAdapter.ViewHolder>(){
        private var runners = ArrayList<Runner>()
        private var selectedPos = RecyclerView.NO_POSITION


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.tab_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val runner = runners[position]
            holder.content.text = runner.cname + " " + runner.surname
            holder.content.setOnClickListener { view ->
                onClickListener(view, runner)
            }
        }

        fun replaceItems(runner: ArrayList<Runner>) {
            this.runners = runner
            notifyDataSetChanged()
        }

        fun addItem(runner: Runner){
            this.runners.add(runner)
            notifyDataSetChanged()
        }

        fun removeItem(runner: Runner){
            this.runners.remove(runner)
            notifyDataSetChanged()

        }

        override fun getItemCount(): Int {
            return runners.size
        }

        inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer
    }

    class TeamMemberAdapter(private val onClickListener: TeamRunnerClickListener, private val context: Context?) : RecyclerView.Adapter<TeamMemberAdapter.ViewHolder>(){

        private var members = ArrayList<Runner>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.team_member_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val member = members[position]
            holder.content.text = member.cname + " " + member.surname
            holder.content.setOnClickListener { view ->
                onClickListener(view, member)
            }

            when(position){
                0 -> holder.imageView.setBackgroundColor(context!!.resources.getColor(R.color.runner1))
                1 -> holder.imageView.setBackgroundColor(context!!.resources.getColor(R.color.runner2))
                2 -> holder.imageView.setBackgroundColor(context!!.resources.getColor(R.color.runner3))
            }
        }

        /*fun replaceItems(runner: ArrayList<Runner>) {
            this.members = runner
            notifyDataSetChanged()
        }*/

        fun addItem(runner: Runner) : Boolean{
            if(members.size < 3){
                this.members.add(runner)
                notifyDataSetChanged()
                return true
            }
            else
                return false
        }

        fun moveUp(runner: Runner){
            var index = this.members.indexOf(runner) - 1
            var buffer = this.members[index]
            this.members[index] = this.members[this.members.indexOf(runner)]
            this.members[index + 1] = buffer
            notifyDataSetChanged()

        }

        fun moveDown(runner: Runner){
            var index = this.members.indexOf(runner) + 1

            if(this.members.size > index){
                var buffer = this.members[index]
                this.members[index] = this.members[this.members.indexOf(runner)]
                this.members[index - 1] = buffer
                notifyDataSetChanged()
            }
        }

        fun getItemPos(runner: Runner): Int{
            return this.members.indexOf(runner)
        }

        fun getItem(position : Int): Runner{
            return this.members[position]
        }

        fun removeItem(runner: Runner){
            this.members.remove(runner)
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return members.size
        }

        inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EquipeTabAjouterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EquipeTabAjouterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

