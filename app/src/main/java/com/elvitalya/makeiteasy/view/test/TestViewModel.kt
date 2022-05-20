package com.elvitalya.makeiteasy.view.test

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor() : ViewModel() {
    fun update(text: TextFieldValue) {
        state = state.copy(text = text, data = state.data.apply { add(text.text) })
    }

    var state by mutableStateOf(TestState())

}


data class TestState(
    var text: TextFieldValue = TextFieldValue(""),
    var data: MutableSet<String> = mutableSetOf()
)