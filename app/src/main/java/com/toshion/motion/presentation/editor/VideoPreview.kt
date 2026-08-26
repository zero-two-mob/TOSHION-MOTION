package com.toshion.motion.presentation.editor

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

/**
 * Real video rendering (PlayerView wrapped for Compose via AndroidView — no
 * first-party Compose-native ExoPlayer surface exists yet at the version
 * this targets, so this is the standard, well-established integration
 * pattern). Custom playback controls live in EditorScreen below this, so
 * PlayerView's own built-in controller is disabled.
 */
@Composable
fun VideoPreview(
    player: ExoPlayer,
    showSafeArea: Boolean,
    showGrid: Boolean,
    modifier: Modifier = Modifier
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var rotationDegrees by remember { mutableFloatStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, rotation ->
                    scale = (scale * zoom).coerceIn(0.5f, 5f)
                    rotationDegrees += rotation
                    offset += pan
                }
            },
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    this.player = player
                    useController = false
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    rotationZ = rotationDegrees
                    translationX = offset.x
                    translationY = offset.y
                }
        )

        if (showSafeArea) SafeAreaOverlay(modifier = Modifier.fillMaxSize())
        if (showGrid) GridOverlay(modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun SafeAreaOverlay(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val marginX = size.width * 0.05f
        val marginY = size.height * 0.05f
        drawRect(
            color = Color.Yellow.copy(alpha = 0.6f),
            topLeft = Offset(marginX, marginY),
            size = Size(size.width - marginX * 2, size.height - marginY * 2),
            style = Stroke(width = 2f)
        )
    }
}

@Composable
private fun GridOverlay(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val thirdWidth = size.width / 3f
        val thirdHeight = size.height / 3f
        val gridColor = Color.White.copy(alpha = 0.4f)
        for (i in 1..2) {
            drawLine(gridColor, Offset(thirdWidth * i, 0f), Offset(thirdWidth * i, size.height), strokeWidth = 1f)
            drawLine(gridColor, Offset(0f, thirdHeight * i), Offset(size.width, thirdHeight * i), strokeWidth = 1f)
        }
    }
}
