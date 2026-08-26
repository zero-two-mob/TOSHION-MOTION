package com.toshion.motion.core.files;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppFileManager_Factory implements Factory<AppFileManager> {
  private final Provider<Context> contextProvider;

  private AppFileManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AppFileManager get() {
    return newInstance(contextProvider.get());
  }

  public static AppFileManager_Factory create(Provider<Context> contextProvider) {
    return new AppFileManager_Factory(contextProvider);
  }

  public static AppFileManager newInstance(Context context) {
    return new AppFileManager(context);
  }
}
