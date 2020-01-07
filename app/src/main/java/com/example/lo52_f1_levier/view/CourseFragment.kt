package com.example.lo52_f1_levier.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lo52_f1_levier.DAO.CourseDao
import com.example.lo52_f1_levier.R
import com.example.lo52_f1_levier.coursetimer.CourseTimerActivity
import com.example.lo52_f1_levier.model.Course
import com.example.lo52_f1_levier.model.Run
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_course.*
import kotlinx.android.synthetic.main.tab_list_content.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CourseFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CourseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var courseDao: CourseDao
    private lateinit var adapter: CourseAdapter

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
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseDao = CourseDao(this.context!!)
        val runs = ArrayList<Run>()
        val cursor = courseDao.getAllCourse()

        with(cursor!!){
            while (moveToNext()){
                val run = Run(getInt(getColumnIndexOrThrow(BaseColumns._ID)),
                    getString(getColumnIndexOrThrow(Course.Coursetable.TITLE)),
                    getString(getColumnIndexOrThrow(Course.Coursetable.DATE)))
                runs.add(run)
            }
        }
        adapter = CourseAdapter(this::redirectToCourseTimer)
        adapter.replaceItems(runs)
        listCourse.layoutManager = LinearLayoutManager(this.context)
        listCourse.adapter = adapter

        newCourse.setOnClickListener {
            val intent = Intent(context, CreateCourseActivity::class.java)
            startActivityForResult(intent, 10001)
        }
    }

    private fun redirectToCourseTimer(courseId: Int) {
        val intent = Intent(context, CourseTimerActivity::class.java)
        intent.putExtra("courseId", courseId)
        startActivity(intent)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {

        super.setUserVisibleHint(isVisibleToUser)

        // Refresh tab data:

        if (fragmentManager != null) {

            fragmentManager!!
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commit()
        }
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

    class CourseAdapter(private val goToCourseTimer: (Int) -> Unit) : RecyclerView.Adapter<CourseAdapter.ViewHolder>(){
        private var courses = ArrayList<Run>()


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.tab_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val course = courses[position]
            holder.content.text = course.name + " : " + course.date
            holder.content.setOnClickListener { goToCourseTimer(course.id) }
        }



        fun replaceItems(course: ArrayList<Run>) {
            this.courses = course
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {
            return courses.size
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
         * @return A new instance of fragment CourseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CourseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
