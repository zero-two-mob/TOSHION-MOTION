package com.toshion.motion.presentation.editor;

import android.content.Context;
import androidx.lifecycle.SavedStateHandle;
import com.toshion.motion.domain.repository.ProjectRepository;
import com.toshion.motion.domain.usecase.AddClipUseCase;
import com.toshion.motion.domain.usecase.DeleteClipUseCase;
import com.toshion.motion.domain.usecase.GetClipsUseCase;
import com.toshion.motion.domain.usecase.SplitClipUseCase;
import com.toshion.motion.domain.usecase.TrimClipUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class EditorViewModel_Factory implements Factory<EditorViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<ProjectRepository> projectRepositoryProvider;

  private final Provider<GetClipsUseCase> getClipsProvider;

  private final Provider<AddClipUseCase> addClipUseCaseProvider;

  private final Provider<TrimClipUseCase> trimClipUseCaseProvider;

  private final Provider<SplitClipUseCase> splitClipUseCaseProvider;

  private final Provider<DeleteClipUseCase> deleteClipUseCaseProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private EditorViewModel_Factory(Provider<Context> contextProvider,
      Provider<ProjectRepository> projectRepositoryProvider,
      Provider<GetClipsUseCase> getClipsProvider, Provider<AddClipUseCase> addClipUseCaseProvider,
      Provider<TrimClipUseCase> trimClipUseCaseProvider,
      Provider<SplitClipUseCase> splitClipUseCaseProvider,
      Provider<DeleteClipUseCase> deleteClipUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.contextProvider = contextProvider;
    this.projectRepositoryProvider = projectRepositoryProvider;
    this.getClipsProvider = getClipsProvider;
    this.addClipUseCaseProvider = addClipUseCaseProvider;
    this.trimClipUseCaseProvider = trimClipUseCaseProvider;
    this.splitClipUseCaseProvider = splitClipUseCaseProvider;
    this.deleteClipUseCaseProvider = deleteClipUseCaseProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public EditorViewModel get() {
    return newInstance(contextProvider.get(), projectRepositoryProvider.get(), getClipsProvider.get(), addClipUseCaseProvider.get(), trimClipUseCaseProvider.get(), splitClipUseCaseProvider.get(), deleteClipUseCaseProvider.get(), savedStateHandleProvider.get());
  }

  public static EditorViewModel_Factory create(Provider<Context> contextProvider,
      Provider<ProjectRepository> projectRepositoryProvider,
      Provider<GetClipsUseCase> getClipsProvider, Provider<AddClipUseCase> addClipUseCaseProvider,
      Provider<TrimClipUseCase> trimClipUseCaseProvider,
      Provider<SplitClipUseCase> splitClipUseCaseProvider,
      Provider<DeleteClipUseCase> deleteClipUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new EditorViewModel_Factory(contextProvider, projectRepositoryProvider, getClipsProvider, addClipUseCaseProvider, trimClipUseCaseProvider, splitClipUseCaseProvider, deleteClipUseCaseProvider, savedStateHandleProvider);
  }

  public static EditorViewModel newInstance(Context context, ProjectRepository projectRepository,
      GetClipsUseCase getClips, AddClipUseCase addClipUseCase, TrimClipUseCase trimClipUseCase,
      SplitClipUseCase splitClipUseCase, DeleteClipUseCase deleteClipUseCase,
      SavedStateHandle savedStateHandle) {
    return new EditorViewModel(context, projectRepository, getClips, addClipUseCase, trimClipUseCase, splitClipUseCase, deleteClipUseCase, savedStateHandle);
  }
}
