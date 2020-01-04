package com.example.lo52_f1_levier.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import com.example.lo52_f1_levier.DAO.CourseDao

import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.model.Run
import kotlinx.android.synthetic.main.fragment_equipe_tab_ajouter.*
import kotlinx.android.synthetic.main.fragment_equipe_tab_consult.*
import kotlinx.android.synthetic.main.fragment_equipe_tab_consult.courseSelector

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
        val spinnerAdapter = ArrayAdapter<Run>(this.context!!,
            android.R.layout.simple_spinner_item, courses)
        courseSelector.adapter = spinnerAdapter

        courseSelector?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val course = courseSelector.selectedItem as Run
                //TODO : load data for this course


            }

        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }*/

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }*/

    /*override fun onDetach() {
        super.onDetach()
        listener = null
    }*/

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
