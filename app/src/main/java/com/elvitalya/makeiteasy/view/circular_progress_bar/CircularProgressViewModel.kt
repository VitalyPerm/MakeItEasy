package com.elvitalya.makeiteasy.view.circular_progress_bar

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

private const val TAG = "CIRCULAR_PROGRESS"

class CircularProgressViewModel : ViewModel() {

    private var decreaseTimer: CountDownTimer? = null

    private var increaseTimer: CountDownTimer? = null

    var tickUp = 0L


    val increase = MutableStateFlow(0L)
    val decrease = MutableStateFlow(0L)

    private fun decreaseCountDown(totalSec: Long) {
        decreaseTimer = object : CountDownTimer(totalSec, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                decrease.tryEmit((millisUntilFinished / 1000))
            }

            override fun onFinish() {
                Log.d(TAG, "onFinish: decreaseCountDown finish")
            }
        }
    }

    private fun increaseCountDown(totalSec: Long) {
        increaseTimer = object : CountDownTimer(totalSec, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tickUp++
                increase.tryEmit(tickUp)
            }

            override fun onFinish() {
                Log.d(TAG, "onFinish: increaseCountDown finish")
            }
        }
    }

    init {
        decreaseCountDown(30000L)
        increaseCountDown(30000L)
        increaseTimer?.start()
        decreaseTimer?.start()
    }
}