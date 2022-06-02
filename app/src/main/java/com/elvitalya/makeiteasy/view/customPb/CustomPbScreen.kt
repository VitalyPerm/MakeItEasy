package com.elvitalya.makeiteasy.view.customPb

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CustomPbScreen(
    vm: CustomPbViewModel = hiltViewModel()
) {


    val timeLine = remember {
        listOf(
            "00:05",
            "00:10",
            "00:15",
            "00:20",
            "00:25",
            "00:30",
            "00:35",
            "00:40"
        )
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
        ) {
            ProgressLine(
                vm.state,
                verticalLine = vm.verticalLineState
            )

            Spacer(modifier = Modifier.height(8.dp))

            Log.d("CHECK___", "Test: ")

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                timeLine.forEachIndexed { i, v ->
                    Text(
                        text = v, fontSize = 10.sp,
                        fontWeight = if (vm.boldTime.contains(i)) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = {
                    if (vm.inProgress) vm.stop() else vm.start()
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(bottom = 15.dp)
            ) {
                Text(text = "Start / Stop")
            }

            Button(
                onClick = {
                    if (vm.inProgress) vm.drawVerticalLine()
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(bottom = 15.dp)
            ) {
                Text(text = "Draw vertical line!")
            }
        }

    }
}


@Composable
fun ProgressLine(
    progress: Float = 0f,
    modifier: Modifier = Modifier,
    verticalLine: List<Float>
) {
    Column(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
        ) {

            drawLine(
                start = Offset(x = 0f, y = size.height),
                end = Offset(x = size.width, y = size.height),
                color = Color(0xFFF7FCFF),
                strokeWidth = size.width * 0.02f,
                cap = StrokeCap.Round,
            )
            drawLine(
                start = Offset(x = 0f, y = size.height),
                end = Offset(x = size.width * progress, y = size.height),
                color = Color(0xFF11B69A),
                strokeWidth = size.width * 0.02f,
                cap = StrokeCap.Round,
            )

            verticalLine.forEach {
                drawLine(
                    start = Offset(x = size.width * it, y = 0f),
                    end = Offset(x = size.width * it, y = size.height * 0.5f),
                    color = Color(0xFF11B69A),
                    strokeWidth = size.width * 0.005f,
                    cap = StrokeCap.Round
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProgressLinePreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        //   ProgressLine(verticalLine = mutableStateListOf())
    }
}

