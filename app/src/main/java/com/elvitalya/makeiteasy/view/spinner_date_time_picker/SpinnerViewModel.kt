package com.elvitalya.makeiteasy.view.spinner_date_time_picker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SpinnerViewModel @Inject constructor() : ViewModel() {

    val time = MutableStateFlow("")

    fun selectDateTime(context: Context) {
        var resultTime = ""
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(context, { _, year, month, day ->
            TimePickerDialog(context, { _, hour, minute ->
                val pickedDateTime = Calendar.getInstance()
                pickedDateTime.set(year, month, day, hour, minute)
                val monthStr: String =
                    if ((month + 1).toString().length == 1) {
                        "0${month + 1}"
                    } else {
                        month.toString()
                    }
                resultTime = "$day - $monthStr - $year $hour:$minute"
                time.tryEmit(resultTime)
            }, startHour, startMinute, true).show()
        }, startYear, startMonth, startDay).show()
    }
}