package com.elvitalya.makeiteasy.view.test

import android.app.Application
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class TestViewModel @Inject constructor(
    private val context: Application
) : ViewModel() {

    var progress by mutableStateOf<Long>(0)

    var pumpList = mutableStateListOf<Long>()

    fun tick() {
        progress += 1
    }

    fun pump() {
        pumpList.add(progress)
        pumpList.distinct()
    }

    init {
        viewModelScope.launch {
            while (true){
                tick()
                delay(1000)
            }
        }
    }
}