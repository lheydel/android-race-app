package com.example.lo52_f1_levier.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lo52_f1_levier.DAO.CoureurDao
import com.example.lo52_f1_levier.DAO.CourseDao
import com.example.lo52_f1_levier.R
import kotlinx.android.synthetic.main.activity_create_course.*

class CreateCourseActivity : AppCompatActivity() {

    private lateinit var courseDao : CourseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_course)
        courseDao = CourseDao(this)

        createCourse.setOnClickListener {
            if(courseName.text.isNotEmpty() && courseDate.text.isNotEmpty()){
                courseDao.insertCourse(courseName.text.toString(),
                    courseDate.text.toString())
                finish()
            }
        }
    }
}
