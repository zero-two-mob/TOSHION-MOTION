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
public final class DeleteClipUseCase_Factory implements Factory<DeleteClipUseCase> {
  private final Provider<ClipRepository> clipRepositoryProvider;

  private DeleteClipUseCase_Factory(Provider<ClipRepository> clipRepositoryProvider) {
    this.clipRepositoryProvider = clipRepositoryProvider;
  }

  @Override
  public DeleteClipUseCase get() {
    return newInstance(clipRepositoryProvider.get());
  }

  public static DeleteClipUseCase_Factory create(Provider<ClipRepository> clipRepositoryProvider) {
    return new DeleteClipUseCase_Factory(clipRepositoryProvider);
  }

  public static DeleteClipUseCase newInstance(ClipRepository clipRepository) {
    return new DeleteClipUseCase(clipRepository);
  }
}
