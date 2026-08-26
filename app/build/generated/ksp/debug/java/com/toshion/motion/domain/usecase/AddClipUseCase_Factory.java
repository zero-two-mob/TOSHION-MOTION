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
public final class AddClipUseCase_Factory implements Factory<AddClipUseCase> {
  private final Provider<ClipRepository> clipRepositoryProvider;

  private AddClipUseCase_Factory(Provider<ClipRepository> clipRepositoryProvider) {
    this.clipRepositoryProvider = clipRepositoryProvider;
  }

  @Override
  public AddClipUseCase get() {
    return newInstance(clipRepositoryProvider.get());
  }

  public static AddClipUseCase_Factory create(Provider<ClipRepository> clipRepositoryProvider) {
    return new AddClipUseCase_Factory(clipRepositoryProvider);
  }

  public static AddClipUseCase newInstance(ClipRepository clipRepository) {
    return new AddClipUseCase(clipRepository);
  }
}
