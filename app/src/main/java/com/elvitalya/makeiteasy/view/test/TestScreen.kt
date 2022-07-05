package com.elvitalya.makeiteasy.view.test

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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

    val progress = (current * 330 / target).toFloat()

    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        val textPaint = Paint().asFrameworkPaint().apply {
            isAntiAlias = true
            textSize = 24f
            color = android.graphics.Color.BLUE
            typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
        }
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(80.dp),
        ) {

            drawArc(
                color = Color.White,
                startAngle = 105f,
                sweepAngle = 330f,
                useCenter = false,
                style = Stroke(10f, cap = StrokeCap.Round)
            )

            drawArc(
                color = Color.Cyan.copy(alpha = 0.5f),
                // startAngle - 90f внизу
                startAngle = 105f,
                // sweep angel - расчетное смещение 0 - 360 (progress)
                sweepAngle = progress,
                useCenter = false,
                style = Stroke(15f, cap = StrokeCap.Round),
            )

            pumpPoint.forEach { pumpPoint ->
                val point = (pumpPoint * 360 / target).toDouble() + 15f



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
            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    progress.toString(),
                    size.width / 2 - 20,
                    size.height,
                    textPaint
                )
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(90.dp)
                .scale(scale),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { pump() }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestPreview() {
    Test()
}