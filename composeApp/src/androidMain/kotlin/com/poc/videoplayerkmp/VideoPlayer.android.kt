package com.poc.videoplayerkmp

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.PlayerSurface
import androidx.media3.ui.compose.SURFACE_TYPE_SURFACE_VIEW
import androidx.media3.ui.compose.modifiers.resizeWithContentScale
import androidx.media3.ui.compose.state.rememberPlayPauseButtonState
import androidx.media3.ui.compose.state.rememberPresentationState

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(url: String) {
    var player by remember { mutableStateOf<ExoPlayer?>(null) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        player = ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))

            prepare()
            playWhenReady = true

            seekTo(0)
            addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    error.printStackTrace()
                }
            })
        }
    }


    player?.let {
        val presentationState = rememberPresentationState(it)
        val scaledModifier = Modifier.resizeWithContentScale(ContentScale.Fit, presentationState.videoSizeDp)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                PlayerSurface(
                    player = it,
                    surfaceType = SURFACE_TYPE_SURFACE_VIEW,
                    modifier = scaledModifier,
                )

                if (presentationState.coverSurface) {
                    Box(Modifier.background(Color(0xFFE91E63)))
                }
            }

            PlayPauseButton(player = it)
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun PlayPauseButton(player: Player, modifier: Modifier = Modifier) {
    val state = rememberPlayPauseButtonState(player)

    IconButton(onClick = state::onClick, modifier = modifier, enabled = state.isEnabled) {
        Icon(
            imageVector = if (state.showPlay) Icons.Default.PlayArrow else Icons.Default.ThumbUp,
            contentDescription = null
        )
    }
}

@Composable
actual fun OpenViewPlayer(url: String) {
    VideoPlayer(url = url)
}