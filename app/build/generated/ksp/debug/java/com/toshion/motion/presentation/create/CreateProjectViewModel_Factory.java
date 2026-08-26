package com.toshion.motion.presentation.create;

import com.toshion.motion.domain.usecase.CreateProjectUseCase;
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
public final class CreateProjectViewModel_Factory implements Factory<CreateProjectViewModel> {
  private final Provider<CreateProjectUseCase> createProjectProvider;

  private CreateProjectViewModel_Factory(Provider<CreateProjectUseCase> createProjectProvider) {
    this.createProjectProvider = createProjectProvider;
  }

  @Override
  public CreateProjectViewModel get() {
    return newInstance(createProjectProvider.get());
  }

  public static CreateProjectViewModel_Factory create(
      Provider<CreateProjectUseCase> createProjectProvider) {
    return new CreateProjectViewModel_Factory(createProjectProvider);
  }

  public static CreateProjectViewModel newInstance(CreateProjectUseCase createProject) {
    return new CreateProjectViewModel(createProject);
  }
}
