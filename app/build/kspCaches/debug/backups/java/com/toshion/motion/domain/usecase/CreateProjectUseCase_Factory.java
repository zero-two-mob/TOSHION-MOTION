package com.toshion.motion.domain.usecase;

import com.toshion.motion.domain.repository.ProjectRepository;
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
public final class CreateProjectUseCase_Factory implements Factory<CreateProjectUseCase> {
  private final Provider<ProjectRepository> projectRepositoryProvider;

  private CreateProjectUseCase_Factory(Provider<ProjectRepository> projectRepositoryProvider) {
    this.projectRepositoryProvider = projectRepositoryProvider;
  }

  @Override
  public CreateProjectUseCase get() {
    return newInstance(projectRepositoryProvider.get());
  }

  public static CreateProjectUseCase_Factory create(
      Provider<ProjectRepository> projectRepositoryProvider) {
    return new CreateProjectUseCase_Factory(projectRepositoryProvider);
  }

  public static CreateProjectUseCase newInstance(ProjectRepository projectRepository) {
    return new CreateProjectUseCase(projectRepository);
  }
}
