package com.toshion.motion.core.logging

import android.util.Log

/**
 * Minimal, dependency-free logging wrapper. Centralizes tagging now so we
 * have one place to add a file/in-app sink later (About > View Logs) without
 * touching every call site.
 */
object Logger {
    private var debugLoggingEnabled = true

    fun init(isDebug: Boolean) {
        debugLoggingEnabled = isDebug
    }

    fun d(tag: String, message: String) {
        if (debugLoggingEnabled) Log.d(tag, message)
    }

    fun i(tag: String, message: String) {
        Log.i(tag, message)
    }

    fun w(tag: String, message: String, throwable: Throwable? = null) {
        Log.w(tag, message, throwable)
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
    }
}
