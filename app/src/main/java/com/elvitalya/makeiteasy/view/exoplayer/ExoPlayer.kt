package com.elvitalya.makeiteasy.view.exoplayer

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.elvitalya.makeiteasy.ui.theme.Purple500
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun ExoPlayer() {

    val context = LocalContext.current
    val url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"

    Column(
        modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Purple500)
        ) {
            Text(
                text = "ExoPlayer Video",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }

        val exoPlayer = remember(context) {
         ExoPlayer.Builder(context).build().apply {
                setMediaItem(MediaItem.fromUri(Uri.parse(url)))
                prepare()
                playWhenReady = true
            }
        }
        AndroidView(factory = {context ->
            StyledPlayerView(context).apply {
                player = exoPlayer
            }
        })
    }
}












