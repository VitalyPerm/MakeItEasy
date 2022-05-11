package com.elvitalya.makeiteasy.view.shimmer_animation

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elvitalya.makeiteasy.ui.theme.Purple500

@Composable
fun ShimmerAnimate() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple500)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Shimmer animate",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            repeat(10) {
                item {
                    ShimmerAnimateItem()
                }
            }
        }
    }
}

@Composable
fun ShimmerAnimateItem() {
    val shimmerColors = listOf(
        Color.LightGray.copy(0.9f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.9f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 1200,
                easing = FastOutLinearInEasing,
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnim.value, translateAnim.value)
    )

    ShimmerItem(brush)
}

@Composable
fun ShimmerItem(brush: Brush) {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier
                    .size(70.dp)
                    .background(brush)
            )

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "",
                        modifier = Modifier
                            .height(20.dp)
                            .weight(1f)
                            .background(brush)
                    )
                    Divider(
                        modifier = Modifier
                            .weight(0.1f)
                    )

                }
                Spacer(modifier = Modifier.padding(5.dp))

                Text(
                    text = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(brush)
                )
            }
        }
    }
}

































