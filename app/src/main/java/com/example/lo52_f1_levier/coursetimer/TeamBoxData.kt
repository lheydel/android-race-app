package com.example.lo52_f1_levier.coursetimer

import com.example.lo52_f1_levier.R

class TeamBoxData(val teamNumber: Int, private val runners: Array<String>) {
    private val NB_PASSAGES = 2
    private val NB_RUNNERS = runners.size
    private val NB_STEPS = 5
    val NB_TOTAL_STEPS = NB_STEPS * NB_RUNNERS * NB_PASSAGES

    var passage = 1
    var runner = 1
    var step = 1
    var totalStepsDone = 0  // could be computed from the previous ones, but it would just be a pain
    var isOver = false

    fun incrementStep() {
        // increment step
        step++
        totalStepsDone++
        if (step <= NB_STEPS) {
            return
        }

        // too much steps, reset counter and increment runner
        step = 1
        runner++
        if (runner <= NB_RUNNERS) {
            return
        }

        // too much runners, reset counter and increment passage
        runner = 1
        passage++
        if (passage <= NB_PASSAGES) {
            return
        }

        // too much passages, course is over
        isOver = true
    }

    fun getCurrentRunner(): String {
        return runners[runner-1]
    }

    fun getDrawableStep(): Int {
        return when (step) {
            1 -> TeamBoxDrawable.STEP_1.id
            2 -> TeamBoxDrawable.STEP_2.id
            3 -> TeamBoxDrawable.STEP_3.id
            4 -> TeamBoxDrawable.STEP_4.id
            else -> TeamBoxDrawable.STEP_5.id
        }
    }

    fun getDrawableRunner(): Int{
        return when(runner) {
            1 -> TeamBoxDrawable.RUNNER_1.id
            2 -> TeamBoxDrawable.RUNNER_2.id
            else -> TeamBoxDrawable.RUNNER_3.id
        }
    }

    fun getDrawablePassage(): Int{
        return when(passage) {
            1 -> TeamBoxDrawable.PASSAGE_1.id
            else -> TeamBoxDrawable.PASSAGE_2.id
        }
    }
}

enum class TeamBoxDrawable(val id: Int) {
    STEP_1(R.drawable.sprint1),
    STEP_2(R.drawable.obstacle1),
    STEP_3(R.drawable.pitstop),
    STEP_4(R.drawable.sprint2),
    STEP_5(R.drawable.obstacle2),

    RUNNER_1(R.drawable.jersey_yellow),
    RUNNER_2(R.drawable.jersey_orange),
    RUNNER_3(R.drawable.jersey_blue),

    PASSAGE_1(R.drawable.passage1),
    PASSAGE_2(R.drawable.passage2),

    FINISH(R.drawable.end_flag);
}