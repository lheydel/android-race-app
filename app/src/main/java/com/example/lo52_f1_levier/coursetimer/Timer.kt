package com.example.lo52_f1_levier.coursetimer

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lo52_f1_levier.R
import kotlinx.android.synthetic.main.fragment_timer.*

class Timer : Fragment() {

    private var started = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startButton.setOnClickListener { startTimer() }
    }

    fun getTimeInMilliseconds(): Long {
        return SystemClock.elapsedRealtime() - timer.base
    }

    private fun startTimer() {
        startButton.visibility = View.GONE
        timer.visibility = View.VISIBLE

        timer.base = SystemClock.elapsedRealtime()
        timer.start()

        started = true
    }

    fun isStarted(): Boolean {
        return started
    }
}
