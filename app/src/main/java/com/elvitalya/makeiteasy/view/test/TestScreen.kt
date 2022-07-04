package com.elvitalya.makeiteasy.view.test

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elvitalya.makeiteasy.R
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Test(
    vm: TestViewModel = hiltViewModel()
) {
    MyProgress(current = vm.progress, pump = { vm.pump() }, pumpPoint = vm.pumpList)
}

@Composable
fun MyProgress(
    target: Long = 300,
    current: Long,
    pumpPoint: List<Long>,
    pump: () -> Unit
) {
    /**
     * 300000 - 5m in millis  - targetProgress
     * 44000 - curr (44 sec)
     * 44000 * 360 / 300000 = 52.8 - sweepAngel
     */

    val progress = (current * 360 / target).toFloat()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(80.dp)
        ) {

            drawCircle(
                color = Color.White,
                radius = size.width / 2f,
                style = Stroke(10f)
            )

            drawArc(
                color = Color.Cyan.copy(alpha = 0.5f),
                // startAngle - 90f внизу
                startAngle = 90f,
                // sweep angel - расчетное смещение 0 - 360 (progress)
                sweepAngle = progress,
                useCenter = false,
                style = Stroke(15f, cap = StrokeCap.Round),
            )

            pumpPoint.forEach { pumpPoint ->
                val point = (pumpPoint * 360 / target).toDouble()
                val x =
                    -(size.height.div(2f) * sin(Math.toRadians(point))).toFloat() + (size.width.div(
                        2f
                    ))
                val y =
                    (size.height.div(2f) * cos(Math.toRadians(point))).toFloat() + (size.height / 2)
                drawCircle(
                    color = Color.Blue,
                    radius = 10f,
                    center = Offset(x, y)
                )
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(90.dp)
                .clip(CircleShape)
                .background(Color.Blue)
                .clickable { pump() },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Animated button", fontSize = 22.sp, color = Color.Black)
        }

    }
}

@Composable
fun ComposeCircularProgressBar(
    modifier: Modifier = Modifier,
    percentage: Float,
    fillColor: Color,
    backgroundColor: Color,
    strokeWidth: Dp
) {
    Canvas(
        modifier = modifier
            .size(150.dp)
            .padding(10.dp)
    ) {
        drawArc(
            color = backgroundColor,
            90f,
            100f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )

        drawArc(
            color = fillColor,
            140f,
            percentage * 260f,
            false,
            style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )


        val angleInDegrees = (percentage * 260.0) + 50.0
        val radius = (size.height / 2)
        val x = -(radius * sin(Math.toRadians(angleInDegrees))).toFloat() + (size.width / 2)
        val y = (radius * cos(Math.toRadians(angleInDegrees))).toFloat() + (size.height / 2)

        drawCircle(
            color = Color.White,
            radius = 10f,
            center = Offset(x, y)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestPreview() {
    Test()
}