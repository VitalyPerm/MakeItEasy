package com.elvitalya.makeiteasy.view.circular_progress_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elvitalya.makeiteasy.ui.theme.Purple500


@Composable
fun CircularProgressBarScreen() {
    val viewModel: CircularProgressViewModel = viewModel()
    val decrease = viewModel.decrease.collectAsState()
    val increase = viewModel.increase.collectAsState()


    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Circular ProgressBar",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.padding(10.dp))


        Text(
            text = "Increase CircularProgress",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.padding(10.dp))

        DecreaseCircularProgressBar(
            percentage = increase.value.toFloat()
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = "Decrease CircularProgress",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.padding(10.dp))
        DecreaseCircularProgressBar(
            percentage = decrease.value.toFloat()
        )
    }
}


@Composable
fun DecreaseCircularProgressBar(
    percentage: Float,
    fontSize: TextUnit = 25.sp,
    radius: Dp = 100.dp,
    strokeWidth: Dp = 10.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }

    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(
        key1 = true
    ) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
        ) {
            drawCircle(
                SolidColor(Color.LightGray),
                radius = size.width / 2,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            val convertValue = (curPercentage.value / 30) * 360

            drawArc(
                brush = Brush.sweepGradient(
                    listOf(
                        Color(0xFFE09121),
                        Color(0xFF808600),
                        Color(0xFFD3212D)
                    )
                ),
                startAngle = -90f,
                sweepAngle = convertValue,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (curPercentage.value).toInt().toString(),
            color = Purple500,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}