package com.elvitalya.makeiteasy.view.test

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor() : ViewModel() {


    var inWorkState = mutableStateListOf<FakeData>()
    var newState = mutableStateListOf<FakeData>()
    var finishedState = mutableStateListOf<FakeData>()

    private suspend fun getList(): List<FakeData> {
        delay(1000)
        return listOf(
            FakeData("Hello", "in_work"),
            FakeData("Opa", "finished"),
            FakeData("Hell", "in_work"),
            FakeData("Puma", "new"),
            FakeData("Pivo", "in_work"),
            FakeData("Hee", "finished"),
            FakeData("Hefdfdlo", "in_work"),
            FakeData("Hellfdfdo", "new"),
            FakeData("Hellgfgfo", "in_work"),
            FakeData("Helgfgflo", "finished"),
            FakeData("Hellgfgfo", "in_work"),
            FakeData("Helgfgflo", "new"),
            FakeData("Helgfgflo", "in_work"),
            FakeData("Hegfgfllo", "finished"),
            FakeData("Hegfgfllo", "in_work"),
            FakeData("Helgfgflo", "new"),
        )
    }

    private fun getLists() {
        viewModelScope.launch {
            val data = getList()
            data.forEach {
                when (it.type) {
                    "in_work" -> inWorkState.add(it)
                    "finished" -> finishedState.add(it)
                    "new" -> newState.add(it)
                }
            }
        }
    }

    init {
        getLists()
    }


}


data class FakeData(
    val title: String,
    val type: String
)