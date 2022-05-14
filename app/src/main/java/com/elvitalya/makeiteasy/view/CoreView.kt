package com.elvitalya.makeiteasy.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(
    title: String,
    bgColor: Color = Color.Cyan,
    contentColor: Color = Color.White
) {
    TopAppBar(
        backgroundColor = bgColor,
        contentColor = contentColor,
        contentPadding = PaddingValues(start = 16.dp)
    ) {
        Text(
            text = title,
            color = Color.Black
        )
    }
}