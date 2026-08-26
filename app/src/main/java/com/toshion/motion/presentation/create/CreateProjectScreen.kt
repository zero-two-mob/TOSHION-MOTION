package com.toshion.motion.presentation.create

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toshion.motion.domain.model.AspectRatio
import com.toshion.motion.domain.model.ResolutionPreset

private val FPS_OPTIONS = listOf(24, 25, 30, 50, 60, 90, 120)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectScreen(
    onDismiss: () -> Unit,
    onProjectCreated: (Long) -> Unit,
    viewModel: CreateProjectViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val mediaPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            // Photo Picker URIs are scoped to this app session — they don't
            // support takePersistableUriPermission the way SAF document URIs
            // do. Phase 3/5's Layer Engine will need to copy the picked file
            // into app storage rather than hold the URI long-term; recorded
            // as-is for now since nothing consumes it yet.
            val mimeType = context.contentResolver.getType(uri)
            viewModel.onMediaPicked(uri.toString(), mimeType, onProjectCreated)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = uiState.projectName,
                onValueChange = viewModel::onNameChange,
                placeholder = { Text("Untitled Project") },
                textStyle = MaterialTheme.typography.headlineSmall,
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            AspectRatio.entries.forEach { ratio ->
                AspectRatioOption(
                    ratio = ratio,
                    isSelected = uiState.aspectRatio == ratio,
                    onClick = { viewModel.onAspectRatioChange(ratio) }
                )
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("Resolution", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ResolutionPreset.entries.forEach { preset ->
                FilterChip(
                    selected = uiState.resolutionPreset == preset,
                    onClick = { viewModel.onResolutionPresetChange(preset) },
                    label = { Text(preset.label) }
                )
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("Frame rate", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FPS_OPTIONS.forEach { fps ->
                FilterChip(
                    selected = uiState.fps == fps,
                    onClick = { viewModel.onFpsChange(fps) },
                    label = { Text("$fps") }
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                mediaPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                )
            },
            enabled = !uiState.isCreating,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            if (uiState.isCreating) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
            } else {
                Text("Import Media & Create Project")
            }
        }

        Spacer(Modifier.height(12.dp))

        IconButton(
            onClick = onDismiss,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Cancel")
        }
    }
}

/** Outline rectangle shaped like the actual ratio, not an icon asset — the
 *  ratio itself communicates the shape, nothing borrowed from anywhere. */
@Composable
private fun AspectRatioOption(
    ratio: AspectRatio,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val maxDim = 40.dp
    val wRatio = ratio.widthRatio.toFloat()
    val hRatio = ratio.heightRatio.toFloat()
    val boxWidth = if (wRatio >= hRatio) maxDim else maxDim * (wRatio / hRatio)
    val boxHeight = if (wRatio >= hRatio) maxDim * (hRatio / wRatio) else maxDim

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier.size(width = maxDim, height = maxDim),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(width = boxWidth, height = boxHeight)
                    .border(
                        width = if (isSelected) 2.dp else 1.dp,
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.outline
                        },
                        shape = RoundedCornerShape(6.dp)
                    )
            )
        }
        Spacer(Modifier.height(6.dp))
        Text(
            text = "${ratio.widthRatio}:${ratio.heightRatio}",
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )
    }
}
