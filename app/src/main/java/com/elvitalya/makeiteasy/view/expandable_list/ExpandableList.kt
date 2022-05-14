package com.elvitalya.makeiteasy.view.expandable_list

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elvitalya.makeiteasy.R
import com.elvitalya.makeiteasy.ui.theme.Purple500
import com.elvitalya.makeiteasy.view.expandable_list.ExpandableConstants.CollapseAnimation
import com.elvitalya.makeiteasy.view.expandable_list.ExpandableConstants.FadeInAnimation
import com.elvitalya.makeiteasy.view.expandable_list.ExpandableConstants.FadeOutAnimation

@Composable
fun ExpandableListScreen(
    vm: ExpandableViewModel = hiltViewModel()
) {
    val cards by vm.cards.collectAsState()
    val expandedCards by vm.expandedCardList.collectAsState()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Cyan),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "ExpandableList",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            itemsIndexed(cards) { index, card ->
                ExpandableCard(
                    card = card,
                    onCardArrowClick = {
                        vm.cardArrowClick(card.id)
                    },
                    expanded = expandedCards.contains(card.id)
                )
            }
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableCard(
    card: ExpandableSampleData,
    onCardArrowClick: () -> Unit,
    expanded: Boolean
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }

    val transition = updateTransition(targetState = transitionState, label = "transition")

    val cardBgColor by transition.animateColor(
        {
            tween(
                durationMillis = ExpandableConstants.ExpandAnimation
            )
        },
        label = "bgColorTransition"
    ) {
        if (expanded) Purple500
        else Purple500
    }
    val cardPaddingHorizontal by transition.animateDp({
        tween(durationMillis = ExpandableConstants.ExpandAnimation)
    }, label = "padding transition") {
        20.dp
    }

    val cardElevation by transition.animateDp({
        tween(durationMillis = ExpandableConstants.ExpandAnimation)
    }, label = "elevation transition") {
        20.dp
    }

    val cardRoundedCorner by transition.animateDp({
        tween(
            durationMillis = ExpandableConstants.ExpandAnimation,
            easing = FastOutSlowInEasing
        )
    }, label = "corner transition") {
        15.dp
    }

    val arrowRotationDegree by transition.animateFloat({
        tween(
            durationMillis = ExpandableConstants.ExpandAnimation,
            easing = FastOutSlowInEasing
        )
    }, label = "corner transition") {
        if (expanded) 0f else 180f
    }

    Card(
        backgroundColor = cardBgColor,
        elevation = cardElevation,
        shape = RoundedCornerShape(cardRoundedCorner),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = cardPaddingHorizontal,
                vertical = 8.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = card.title,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(12.dp)
                )
                IconButton(onClick = onCardArrowClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_expand_less),
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(arrowRotationDegree)
                            .size(55.dp),
                        tint = Cyan
                    )
                }
            }

            ExpandableContent(expanded = expanded)
        }
    }
}


@Composable
fun ExpandableContent(expanded: Boolean) {

    val enterFadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = FadeInAnimation,
                easing = FastOutLinearInEasing
            )
        )
    }

    val exitFadeOut = remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = FadeOutAnimation,
                easing = LinearOutSlowInEasing
            )
        )
    }

    val exitCollapse = remember { shrinkVertically(animationSpec = tween(CollapseAnimation)) }
    val enterExpand = remember { expandVertically(animationSpec = tween(CollapseAnimation)) }

    AnimatedVisibility(
        visible = expanded,
        enter = enterExpand,
        exit = exitCollapse
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        ) {
            Text(
                text = "Some text description",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Black
            )
        }
    }
}
