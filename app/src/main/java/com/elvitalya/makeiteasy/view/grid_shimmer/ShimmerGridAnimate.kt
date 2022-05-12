package com.elvitalya.makeiteasy.view.grid_shimmer

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elvitalya.makeiteasy.ui.theme.Purple500

@Composable
fun ShimmerGridAnimate() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .align(Alignment.TopCenter)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Shimmer Grid Animate",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(top = 60.dp)
                .align(Alignment.TopCenter)
        ) {
            repeat(25) {
                item {
                    ShimmerAnimatedGridItem()
                }
            }
        }

    }
}

@Composable
fun ShimmerAnimatedGridItem() {

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(
                durationMillis = 1200,
                easing = FastOutLinearInEasing
            ),
            RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnim.value, translateAnim.value)
    )

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(
            modifier = Modifier
                .size(100.dp)
                .background(brush)
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = "",
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(0.8f)
                .background(brush)
        )

        Spacer(modifier = Modifier.padding(3.dp))

        Text(
            text = "",
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth(0.8f)
                .background(brush)
        )
    }
}