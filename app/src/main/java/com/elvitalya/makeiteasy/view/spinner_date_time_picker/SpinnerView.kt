package com.elvitalya.makeiteasy.view.spinner_date_time_picker

import android.annotation.SuppressLint
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elvitalya.makeiteasy.view.AppBar
import kotlin.math.exp

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun SpinnerScreen(
    vm: SpinnerViewModel = hiltViewModel()
) {
    val dateTime = vm.time.collectAsState()

    val sampleList = mutableListOf(
        "Sample 1", "Sample 2", "Sample 3", "Sample 4", "Sample 5", "Sample 6"
    )
    var sampleName: String by remember { mutableStateOf(sampleList[0]) }

    var expanded by remember { mutableStateOf(false) }

    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }

    val transition = updateTransition(targetState = transitionState, label = "transition")

    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = 300)
    }, label = "rotationDegree") {
        if (expanded) 180f else 0f
    }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppBar(title = "Spinner & DateTime")
        }
    ) {
        Box(
            Modifier
                .fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        expanded = !expanded
                    }
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = sampleName,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .height(30.dp)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(arrowRotationDegree)
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    sampleList.forEach { data ->
                        DropdownMenuItem(
                            onClick = {
                                expanded = false
                                sampleName = data
                            }
                        ) {
                            Text(text = data)
                        }
                    }
                }
            }

            Column(
                Modifier
                    .align(Alignment.Center)
            ) {
                TextButton(
                    onClick = {
                        vm.selectDateTime(context)
                    }, modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.LightGray)
                        .padding(5.dp)
                ) {
                    Text(
                        text = "Select date",
                        color = Color.Yellow
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = dateTime.value.ifBlank { "Choose date" })
            }
        }
    }
}