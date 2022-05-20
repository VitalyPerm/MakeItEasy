package com.elvitalya.makeiteasy.view.bottom_sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.elvitalya.makeiteasy.R
import com.elvitalya.makeiteasy.ui.theme.Purple500
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet() {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )
    val sheetToggle: () -> Unit = {
        scope.launch {
            if (scaffoldState.bottomSheetState.isCollapsed) {
                scaffoldState.bottomSheetState.expand()
            } else {
                scaffoldState.bottomSheetState.collapse()
            }
        }
    }

    val radius = (30 * scaffoldState.currentFraction).dp

    BottomSheetScaffold(
        sheetContent = {
            SheetContent {
                SheetExpanded {
                    RadioScreenLarge()
                }
                SheetCollapsed(
                    isCollapsed = scaffoldState.bottomSheetState.isCollapsed,
                    currentFraction = scaffoldState.currentFraction,
                    onSheetClick = { sheetToggle }
                ) {
                    RadioScreenSmall()
                }
            }
        },
        sheetPeekHeight = 70.dp,
        modifier = Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topStart = radius, topEnd = radius),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = CenterVertically
            ) {
                Text(
                    text = "Bottom sheet",
                    modifier = Modifier
                    .padding(5.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) {

    }

}


@Composable
fun SheetContent(
    heightFraction: Float = 0.8f,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = heightFraction)
    ) {
        content()
    }
}

@Composable
fun SheetExpanded(
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple500.copy(alpha = 0.3f))
    ) {
        content()
    }
}

@Composable
fun SheetCollapsed(
    isCollapsed: Boolean,
    currentFraction: Float,
    onSheetClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.Yellow)
            .graphicsLayer(alpha = 1f - currentFraction)
            .noRippleClickable(
                onClick = onSheetClick,
                enabled = isCollapsed
            ),
        verticalAlignment = CenterVertically
    ) {
        content()
    }
}


@Composable
fun TopSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        RadioLogo()

        Text(
            text = "FM Title",
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(15.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
fun PlayerControls(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp, 0.dp, 8.dp)
            .background(Color.Blue)
    ) {
        IconButton(
            onClick = {

            },
            modifier = Modifier
                .padding(15.dp, 8.dp, 8.dp, 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_volume_up_24),
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .weight(1f),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .padding(8.dp, 8.dp, 8.dp, 15.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_skip_previous_24),
                    contentDescription = null
                )
            }

            PlayPauseButton(onClick = {})

            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .padding(8.dp, 8.dp, 8.dp, 15.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_skip_next_24),
                    contentDescription = null
                )
            }

            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .padding(8.dp, 8.dp, 8.dp, 15.dp)
            ) {
                Image(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun PlayPauseButton(
    onClick: () -> Unit
) {
    CircleIconButtonLarge(
        backGround = Color.White,
        imageVector = Icons.Filled.PlayArrow,
        onClick = onClick
    )
}

@Composable
fun CircleIconButtonLarge(
    backGround: Color,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Center,
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(backGround, CircleShape)
            .clickable(
                indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick() }
            )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}


@Composable
fun RowScope.RadioScreenSmall() {
    RadioLogoSmall(
        modifier = Modifier
            .padding(6.dp)
    )

    Text(
        text = "Fm title",
        color = Color.Black,
        style = MaterialTheme.typography.caption,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        modifier = Modifier
            .weight(1f)
            .padding(15.dp)
    )

    IconButton(
        onClick = {

        },
        modifier = Modifier
            .padding(8.dp, 8.dp, 8.dp, 15.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = Color.Blue
        )
    }

}

@Composable
fun RadioLogoSmall(
    modifier: Modifier = Modifier,
    placeHolder: Modifier = modifier
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = R.drawable.ic_baseline_radio_24)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                transformations(CircleCropTransformation())
            }).build()
    )


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .shadow(1.dp, CircleShape)
            .background(
                color = Color.LightGray,
                shape = CircleShape
            )
            .then(
                placeHolder
            )
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)

        )

        when (painter.state) {
            is AsyncImagePainter.State.Error -> {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_radio_24),
                    contentDescription = null,
                    tint = Color.Cyan,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}


@Composable
fun BoxScope.RadioScreenLarge() {
    Surface {
        RadioPlayer(
            topSection = {
                TopSection()
            },
            playerControls = {
                PlayerControls()
            }
        )
    }
}

@Composable
fun RadioLogo() {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = R.drawable.ic_baseline_radio_24)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
            }).build()
    )


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(250.dp)
            .shadow(30.dp, CircleShape)
            .background(
                color = Color.LightGray,
                shape = CircleShape
            )
            .border(10.dp, Color.Red, CircleShape)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .padding(15.dp)
        )

        when (painter.state) {
            is AsyncImagePainter.State.Error -> {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_radio_24),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(70.dp)
                )
            }
        }
    }
}

@Composable
fun RadioPlayer(
    topSection: @Composable () -> Unit,
    playerControls: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(35.dp)
            ) {
                topSection()
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
        ) {
            Spacer(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            bottomStartPercent = 100,
                            bottomEndPercent = 100
                        )
                    )
                    .background(Purple500)
                    .height(35.dp)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp),
                contentAlignment = Alignment.Center
            ) {
                playerControls()
            }
        }
    }
}

inline fun Modifier.noRippleClickable(
    enabled: Boolean,
    noinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        enabled = enabled,
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}


@OptIn(ExperimentalMaterialApi::class)
val BottomSheetScaffoldState.currentFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 0f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 1f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> fraction
            else -> 1f - fraction
        }
    }