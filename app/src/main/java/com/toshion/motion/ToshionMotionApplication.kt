package com.toshion.motion

import android.app.Application
import com.toshion.motion.core.crash.CrashHandler
import com.toshion.motion.core.logging.Logger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToshionMotionApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.init(isDebug = BuildConfig.DEBUG)
        CrashHandler.install(applicationContext)
        Logger.i(TAG, "ToshionMotion starting — versionName=${BuildConfig.VERSION_NAME}")
    }

    private companion object {
        const val TAG = "ToshionMotionApplication"
    }
}
