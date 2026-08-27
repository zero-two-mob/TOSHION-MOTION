# Add project specific ProGuard rules here.
# Release minification is off for Phase 1 (isMinifyEnabled = false) while the
# architecture is still taking shape. Real keep-rules for Room, MediaCodec
# reflection, and any native/JNI boundaries get added when we enable it
# properly in the Performance Optimization phase.

# Hilt/Dagger
-keep class dagger.hilt.android.internal.managers.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$ComponentManager { *; }
-keep class * implements dagger.hilt.internal.GeneratedComponent { *; }
-keep class * implements dagger.hilt.internal.ComponentEntryPoint { *; }
-keep class * implements dagger.hilt.android.internal.builders.ActivityComponentBuilder { *; }
-keep class * implements dagger.hilt.android.internal.builders.ServiceComponentBuilder { *; }
-keep class * implements dagger.hilt.android.internal.builders.ViewComponentBuilder { *; }
-keep class * implements dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder { *; }
-keep class * implements dagger.hilt.android.internal.builders.FragmentComponentBuilder { *; }
-keep class * implements dagger.hilt.android.internal.builders.ViewComponentBuilder { *; }

# Room
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**

# Compose
-keepclassmembers class androidx.compose.ui.platform.AndroidComposeView {
    void *;
}
-keepclassmembers class * extends androidx.compose.runtime.Composer {
    void *;
}

# Kotlin Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembernames class kotlinx.coroutines.android.HandlerContext$ScheduledPatch {
    volatile <fields>;
}

# General optimization
-repackageclasses ''
-allowaccessmodification
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
