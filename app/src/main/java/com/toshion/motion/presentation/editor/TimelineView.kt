package com.toshion.motion.presentation.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.toshion.motion.domain.model.Clip

private val TIMELINE_HEIGHT = 96.dp
private val RULER_HEIGHT = 20.dp
private val MIN_CLIP_WIDTH = 24.dp
private const val MIN_CLIP_DURATION_MS = 200L

@Composable
fun TimelineView(
    clips: List<Clip>,
    selectedClipId: Long?,
    currentPositionMs: Long,
    zoomPxPerSecond: Float,
    onSelectClip: (Long?) -> Unit,
    onSeekTo: (Long) -> Unit,
    onZoomChange: (Float) -> Unit,
    onAddClip: () -> Unit,
    onSplit: () -> Unit,
    onDeleteSelected: () -> Unit,
    onTrimSelected: (newTrimStartMs: Long, newTrimEndMs: Long?) -> Unit,
    modifier: Modifier = Modifier
) {
    val totalDurationMs = clips.sumOf { it.durationMs }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onAddClip) {
                Icon(Icons.Default.Add, contentDescription = "Add clip")
            }
            IconButton(onClick = onSplit, enabled = clips.isNotEmpty()) {
                Icon(Icons.Default.ContentCut, contentDescription = "Split at playhead")
            }
            IconButton(onClick = onDeleteSelected, enabled = selectedClipId != null) {
                Icon(Icons.Default.Delete, contentDescription = "Delete selected clip")
            }
            Spacer(Modifier.weight(1f))
            IconButton(onClick = { onZoomChange(zoomPxPerSecond - 20f) }) {
                Icon(Icons.Default.ZoomOut, contentDescription = "Zoom out")
            }
            IconButton(onClick = { onZoomChange(zoomPxPerSecond + 20f) }) {
                Icon(Icons.Default.ZoomIn, contentDescription = "Zoom in")
            }
        }

        if (clips.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(TIMELINE_HEIGHT),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No clips yet \u2014 tap + to add one",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            val timelineWidth = msToDp(totalDurationMs.coerceAtLeast(1000L), zoomPxPerSecond)
            val scrollState = rememberScrollState()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
            ) {
                Box(
                    modifier = Modifier
                        .width(timelineWidth)
                        .height(TIMELINE_HEIGHT)
                        .pointerInput(zoomPxPerSecond, totalDurationMs) {
                            detectTapGestures { offset ->
                                val tappedMs = (offset.x / zoomPxPerSecond * 1000).toLong()
                                onSeekTo(tappedMs.coerceIn(0L, totalDurationMs))
                            }
                        }
                ) {
                    Column {
                        TimelineRuler(
                            totalDurationMs = totalDurationMs,
                            zoomPxPerSecond = zoomPxPerSecond,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(RULER_HEIGHT)
                        )
                        Row(modifier = Modifier.fillMaxWidth()) {
                            clips.forEach { clip ->
                                ClipBlock(
                                    clip = clip,
                                    isSelected = clip.id == selectedClipId,
                                    widthDp = msToDp(clip.durationMs, zoomPxPerSecond),
                                    zoomPxPerSecond = zoomPxPerSecond,
                                    onSelect = { onSelectClip(clip.id) },
                                    onTrim = onTrimSelected
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .offset(x = msToDp(currentPositionMs, zoomPxPerSecond))
                            .width(2.dp)
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.error)
                    )
                }
            }
        }
    }
}

@Composable
private fun TimelineRuler(totalDurationMs: Long, zoomPxPerSecond: Float, modifier: Modifier = Modifier) {
    val totalSeconds = (totalDurationMs / 1000).toInt().coerceAtLeast(1)
    val markerIntervalSeconds = when {
        zoomPxPerSecond >= 100f -> 1
        zoomPxPerSecond >= 40f -> 5
        else -> 10
    }
    Box(modifier = modifier) {
        var second = 0
        while (second <= totalSeconds) {
            Text(
                text = formatRulerTime(second * 1000L),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.offset(x = (second * zoomPxPerSecond).dp)
            )
            second += markerIntervalSeconds
        }
    }
}

@Composable
private fun ClipBlock(
    clip: Clip,
    isSelected: Boolean,
    widthDp: Dp,
    zoomPxPerSecond: Float,
    onSelect: () -> Unit,
    onTrim: (newTrimStartMs: Long, newTrimEndMs: Long?) -> Unit
) {
    Box(
        modifier = Modifier
            .width(if (widthDp < MIN_CLIP_WIDTH) MIN_CLIP_WIDTH else widthDp)
            .fillMaxHeight()
            .padding(horizontal = 1.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.secondaryContainer
                }
            )
            .clickable(onClick = onSelect)
    ) {
        Text(
            text = clip.mediaUri.substringAfterLast('/').take(14),
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(4.dp)
        )

        if (isSelected) {
            TrimHandle(
                alignment = Alignment.CenterStart,
                zoomPxPerSecond = zoomPxPerSecond,
                onDragDeltaMs = { deltaMs ->
                    val newStart = (clip.trimStartMs + deltaMs)
                        .coerceIn(0L, clip.effectiveTrimEndMs - MIN_CLIP_DURATION_MS)
                    onTrim(newStart, clip.trimEndMs)
                }
            )
            TrimHandle(
                alignment = Alignment.CenterEnd,
                zoomPxPerSecond = zoomPxPerSecond,
                onDragDeltaMs = { deltaMs ->
                    val newEnd = (clip.effectiveTrimEndMs + deltaMs)
                        .coerceIn(clip.trimStartMs + MIN_CLIP_DURATION_MS, clip.sourceDurationMs)
                    onTrim(clip.trimStartMs, newEnd)
                }
            )
        }
    }
}

/**
 * Accumulates the total drag distance locally and commits exactly once on
 * release, rather than calling [onDragDeltaMs] every drag frame — a trim
 * commit means a DB write, a re-pack of every clip after it, and an
 * ExoPlayer playlist rebuild, none of which should happen dozens of times a
 * second while a finger is moving.
 */
@Composable
private fun BoxScope.TrimHandle(
    alignment: Alignment,
    zoomPxPerSecond: Float,
    onDragDeltaMs: (Long) -> Unit
) {
    var accumulatedPx by remember { mutableFloatStateOf(0f) }
    Box(
        modifier = Modifier
            .align(alignment)
            .width(14.dp)
            .fillMaxHeight()
            .background(Color.White.copy(alpha = 0.7f))
            .pointerInput(zoomPxPerSecond) {
                detectDragGestures(
                    onDragStart = { accumulatedPx = 0f },
                    onDragEnd = {
                        val deltaMs = (accumulatedPx / zoomPxPerSecond * 1000).toLong()
                        if (deltaMs != 0L) onDragDeltaMs(deltaMs)
                        accumulatedPx = 0f
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        accumulatedPx += dragAmount.x
                    }
                )
            }
    )
}

private fun msToDp(ms: Long, pxPerSecond: Float): Dp = ((ms / 1000f) * pxPerSecond).dp

private fun formatRulerTime(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}
