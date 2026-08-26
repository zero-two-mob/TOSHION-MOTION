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
public final class GetClipsUseCase_Factory implements Factory<GetClipsUseCase> {
  private final Provider<ClipRepository> clipRepositoryProvider;

  private GetClipsUseCase_Factory(Provider<ClipRepository> clipRepositoryProvider) {
    this.clipRepositoryProvider = clipRepositoryProvider;
  }

  @Override
  public GetClipsUseCase get() {
    return newInstance(clipRepositoryProvider.get());
  }

  public static GetClipsUseCase_Factory create(Provider<ClipRepository> clipRepositoryProvider) {
    return new GetClipsUseCase_Factory(clipRepositoryProvider);
  }

  public static GetClipsUseCase newInstance(ClipRepository clipRepository) {
    return new GetClipsUseCase(clipRepository);
  }
}
