package com.toshion.motion.presentation.projects;

import com.toshion.motion.domain.usecase.DeleteProjectUseCase;
import com.toshion.motion.domain.usecase.DuplicateProjectUseCase;
import com.toshion.motion.domain.usecase.GetProjectsUseCase;
import com.toshion.motion.domain.usecase.RenameProjectUseCase;
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
public final class ProjectsViewModel_Factory implements Factory<ProjectsViewModel> {
  private final Provider<GetProjectsUseCase> getProjectsProvider;

  private final Provider<DeleteProjectUseCase> deleteProjectProvider;

  private final Provider<DuplicateProjectUseCase> duplicateProjectProvider;

  private final Provider<RenameProjectUseCase> renameProjectProvider;

  private ProjectsViewModel_Factory(Provider<GetProjectsUseCase> getProjectsProvider,
      Provider<DeleteProjectUseCase> deleteProjectProvider,
      Provider<DuplicateProjectUseCase> duplicateProjectProvider,
      Provider<RenameProjectUseCase> renameProjectProvider) {
    this.getProjectsProvider = getProjectsProvider;
    this.deleteProjectProvider = deleteProjectProvider;
    this.duplicateProjectProvider = duplicateProjectProvider;
    this.renameProjectProvider = renameProjectProvider;
  }

  @Override
  public ProjectsViewModel get() {
    return newInstance(getProjectsProvider.get(), deleteProjectProvider.get(), duplicateProjectProvider.get(), renameProjectProvider.get());
  }

  public static ProjectsViewModel_Factory create(Provider<GetProjectsUseCase> getProjectsProvider,
      Provider<DeleteProjectUseCase> deleteProjectProvider,
      Provider<DuplicateProjectUseCase> duplicateProjectProvider,
      Provider<RenameProjectUseCase> renameProjectProvider) {
    return new ProjectsViewModel_Factory(getProjectsProvider, deleteProjectProvider, duplicateProjectProvider, renameProjectProvider);
  }

  public static ProjectsViewModel newInstance(GetProjectsUseCase getProjects,
      DeleteProjectUseCase deleteProject, DuplicateProjectUseCase duplicateProject,
      RenameProjectUseCase renameProject) {
    return new ProjectsViewModel(getProjects, deleteProject, duplicateProject, renameProject);
  }
}
