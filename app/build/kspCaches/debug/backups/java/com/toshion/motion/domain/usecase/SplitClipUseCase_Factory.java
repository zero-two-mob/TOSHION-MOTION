package com.toshion.motion.domain.usecase;

import com.toshion.motion.domain.repository.ClipRepository;
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
public final class SplitClipUseCase_Factory implements Factory<SplitClipUseCase> {
  private final Provider<ClipRepository> clipRepositoryProvider;

  private SplitClipUseCase_Factory(Provider<ClipRepository> clipRepositoryProvider) {
    this.clipRepositoryProvider = clipRepositoryProvider;
  }

  @Override
  public SplitClipUseCase get() {
    return newInstance(clipRepositoryProvider.get());
  }

  public static SplitClipUseCase_Factory create(Provider<ClipRepository> clipRepositoryProvider) {
    return new SplitClipUseCase_Factory(clipRepositoryProvider);
  }

  public static SplitClipUseCase newInstance(ClipRepository clipRepository) {
    return new SplitClipUseCase(clipRepository);
  }
}
