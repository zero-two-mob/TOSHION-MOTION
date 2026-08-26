package com.toshion.motion.presentation.settings;

import com.toshion.motion.core.files.AppFileManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
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
public final class DeveloperOptionsViewModel_Factory implements Factory<DeveloperOptionsViewModel> {
  private final Provider<AppFileManager> appFileManagerProvider;

  private DeveloperOptionsViewModel_Factory(Provider<AppFileManager> appFileManagerProvider) {
    this.appFileManagerProvider = appFileManagerProvider;
  }

  @Override
  public DeveloperOptionsViewModel get() {
    return newInstance(appFileManagerProvider.get());
  }

  public static DeveloperOptionsViewModel_Factory create(
      Provider<AppFileManager> appFileManagerProvider) {
    return new DeveloperOptionsViewModel_Factory(appFileManagerProvider);
  }

  public static DeveloperOptionsViewModel newInstance(AppFileManager appFileManager) {
    return new DeveloperOptionsViewModel(appFileManager);
  }
}
