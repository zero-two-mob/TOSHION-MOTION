package com.toshion.motion.core.crash

import android.content.Context
import android.util.Log
import com.toshion.motion.core.logging.Logger
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Global uncaught-exception handler. Logs the crash, writes a timestamped
 * report to app-private storage (a future Settings > About screen will
 * surface these), then hands off to the platform's default handler so the
 * process still terminates normally instead of hanging in a bad state.
 */
object CrashHandler {

    fun install(context: Context) {
        val appContext = context.applicationContext
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            try {
                Logger.e("CrashHandler", "Uncaught exception on ${thread.name}", throwable)
                writeReport(appContext, throwable)
            } catch (writeFailure: Exception) {
                Log.e("CrashHandler", "Failed to write crash report", writeFailure)
            } finally {
                defaultHandler?.uncaughtException(thread, throwable)
            }
        }
    }

    private fun writeReport(context: Context, throwable: Throwable) {
        val dir = File(context.filesDir, "crash_reports").apply { mkdirs() }
        val timestamp = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US).format(Date())
        val file = File(dir, "crash_$timestamp.txt")

        val stackTrace = StringWriter().also { sw ->
            throwable.printStackTrace(PrintWriter(sw))
        }.toString()

        file.writeText(
            buildString {
                appendLine("ToshionMotion crash report")
                appendLine("Timestamp: $timestamp")
                appendLine()
                append(stackTrace)
            }
        )
    }
}
