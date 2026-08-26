package com.toshion.motion.presentation.settings

import androidx.lifecycle.ViewModel
import com.toshion.motion.core.files.AppFileManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DeveloperOptionsViewModel @Inject constructor(
    private val appFileManager: AppFileManager
) : ViewModel() {

    private val _crashReports = MutableStateFlow<List<String>>(emptyList())
    val crashReports: StateFlow<List<String>> = _crashReports.asStateFlow()

    private val _cacheSizeBytes = MutableStateFlow(0L)
    val cacheSizeBytes: StateFlow<Long> = _cacheSizeBytes.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        _crashReports.value = appFileManager.crashReportsDir()
            .listFiles()
            ?.sortedByDescending { it.lastModified() }
            ?.map { it.name }
            ?: emptyList()
        _cacheSizeBytes.value = appFileManager.cacheSizeBytes()
    }

    fun clearCache() {
        appFileManager.clearCache()
        refresh()
    }
}
