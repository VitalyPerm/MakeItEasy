package com.elvitalya.makeiteasy.view.customPb

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CustomPbViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {

    var state by mutableStateOf(0f)

    var verticalLineState = mutableStateListOf<Float>()

    var boldTime = mutableStateListOf<Int>()

    var inProgress = false

    var timerJob: Job? = null

    fun start() {
        inProgress = true
        timerJob = viewModelScope.launch {
            while (true) {
                if (state > 1.0f) state = 0.0f
                state += 0.01f
                delay(500)
            }
        }
    }

    fun stop() {
        inProgress = false
        timerJob?.cancel()
    }


    fun drawVerticalLine() {
        verticalLineState.add(state)
        when (state) {
            in 0f..0.125f -> boldTime.add(0)
            in 0.126f..0.25f -> boldTime.add(1)
            in 0.256f..0.375f -> boldTime.add(2)
            in 0.376f..0.5f -> boldTime.add(3)
            in 0.501f..0.625f -> boldTime.add(4)
            in 0.626f..0.75f -> boldTime.add(5)
            in 0.751f..0.875f -> boldTime.add(6)
            in 0.876f..1f -> boldTime.add(7)
        }
    }
}