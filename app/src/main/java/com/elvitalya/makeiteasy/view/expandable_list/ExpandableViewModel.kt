package com.elvitalya.makeiteasy.view.expandable_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpandableViewModel @Inject constructor() : ViewModel() {

    val cards = MutableStateFlow(listOf<ExpandableSampleData>())


    val expandedCardList = MutableStateFlow(listOf<Int>())


    init {
        getSampleData()
    }

    private fun getSampleData() {
        viewModelScope.launch(Dispatchers.Default) {
            val sampleList = arrayListOf<ExpandableSampleData>()
            repeat(10) {
                sampleList += ExpandableSampleData(
                    id = it,
                    title = "Make $it"
                )
            }
            cards.emit(sampleList)
        }
    }


    fun cardArrowClick(cardId: Int) {
        expandedCardList.value = expandedCardList.value.toMutableList().also { list ->
            cardId.let {
                if (list.contains(it)) list.remove(it) else list.add(it)
            }
        }
    }

}