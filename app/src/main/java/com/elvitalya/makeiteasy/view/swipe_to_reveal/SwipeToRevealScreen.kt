package com.elvitalya.makeiteasy.view.swipe_to_reveal

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elvitalya.makeiteasy.R
import kotlin.math.roundToInt
import kotlin.random.Random

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToRevealScreen() {
    var bgColor by remember { mutableStateOf(Color.Cyan) }

    val color =
        animateColorAsState(
            targetValue = bgColor,
            animationSpec =
            tween(durationMillis = 2000)
        )

    val squareSize = 150.dp
    val swipeState = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue.copy(alpha = 0.7f))
                .align(Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Compose Swipe",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(15.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.LightGray)
                    .swipeable(
                        state = swipeState,
                        anchors = anchors,
                        thresholds = { _, _ ->
                            FractionalThreshold(0.3f)
                        },
                        orientation = Orientation.Horizontal
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    IconButton(
                        onClick = {
                            bgColor = bgColor.copy(alpha = 0.3f)
                        },
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Blue)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = Color.Yellow
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    IconButton(
                        onClick = {
                            bgColor = Color(
                                red = (0..255).random(),
                                green = (0..255).random(),
                                blue = (0..255).random()
                            )
                        },
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.Blue)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .offset {
                            IntOffset(swipeState.offset.value.roundToInt(), 0)
                        }
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .height(150.dp)
                        .fillMaxHeight()
                        .background(color.value)
                        .align(Alignment.CenterStart)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.jetpack_compose),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Column(

                            ) {
                                Text(
                                    text = "Swipe Layout",
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.padding(10.dp))

                                Text(
                                    text = " Hmm some text here I Dont care",
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}