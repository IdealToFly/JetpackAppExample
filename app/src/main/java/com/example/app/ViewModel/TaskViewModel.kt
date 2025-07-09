package com.example.app.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    var taskDate by mutableStateOf("学习周期:2022.01.01-2022.12.31")
        private set

    var totalPointOfYear = 13500

    var pointOfYear by mutableStateOf(10000)
        private set

    var pointOfYearPercent by mutableStateOf(0f)
        private set

    fun updatePointPercent() {
        pointOfYearPercent = 220f * pointOfYear / totalPointOfYear
    }

    var pointsOfWeek by mutableStateOf(listOf(0.0, 2.0, 6.0, 9.5, 10.0, 15.0, 5.0))
        private set

    val weeks = listOf("02.05", "02.06", "02.07", "02.08", "02.09","02.10", "Today")

    private var todayPoint = 13

    var tips by mutableStateOf("今日获得0积分，快去完成下面任务吧")
        private set

    fun updateTips() {
        var a = if (todayPoint == 0) {
            tips = "今日获得0积分，快去完成下面任务吧"
        } else if(todayPoint in 1..14){
            tips = "今日获得${todayPoint}积分，快去完成下面任务吧"
        } else {
            tips = "今日获得${todayPoint}，已经完成任务"
        }
    }



}