package com.toshion.motion;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.toshion.motion.core.files.AppFileManager;
import com.toshion.motion.data.local.datastore.SettingsDataStore;
import com.toshion.motion.data.local.db.ToshionMotionDatabase;
import com.toshion.motion.data.local.db.dao.ClipDao;
import com.toshion.motion.data.local.db.dao.ProjectDao;
import com.toshion.motion.data.repository.ClipRepositoryImpl;
import com.toshion.motion.data.repository.ProjectRepositoryImpl;
import com.toshion.motion.data.repository.SettingsRepositoryImpl;
import com.toshion.motion.di.DatabaseModule_ProvideClipDaoFactory;
import com.toshion.motion.di.DatabaseModule_ProvideDatabaseFactory;
import com.toshion.motion.di.DatabaseModule_ProvideProjectDaoFactory;
import com.toshion.motion.domain.repository.ClipRepository;
import com.toshion.motion.domain.repository.ProjectRepository;
import com.toshion.motion.domain.repository.SettingsRepository;
import com.toshion.motion.domain.usecase.AddClipUseCase;
import com.toshion.motion.domain.usecase.CreateProjectUseCase;
import com.toshion.motion.domain.usecase.DeleteClipUseCase;
import com.toshion.motion.domain.usecase.DeleteProjectUseCase;
import com.toshion.motion.domain.usecase.DuplicateProjectUseCase;
import com.toshion.motion.domain.usecase.GetClipsUseCase;
import com.toshion.motion.domain.usecase.GetProjectsUseCase;
import com.toshion.motion.domain.usecase.RenameProjectUseCase;
import com.toshion.motion.domain.usecase.SplitClipUseCase;
import com.toshion.motion.domain.usecase.TrimClipUseCase;
import com.toshion.motion.presentation.create.CreateProjectViewModel;
import com.toshion.motion.presentation.create.CreateProjectViewModel_HiltModules;
import com.toshion.motion.presentation.create.CreateProjectViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.toshion.motion.presentation.create.CreateProjectViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.toshion.motion.presentation.editor.EditorViewModel;
import com.toshion.motion.presentation.editor.EditorViewModel_HiltModules;
import com.toshion.motion.presentation.editor.EditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.toshion.motion.presentation.editor.EditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.toshion.motion.presentation.projects.ProjectsViewModel;
import com.toshion.motion.presentation.projects.ProjectsViewModel_HiltModules;
import com.toshion.motion.presentation.projects.ProjectsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.toshion.motion.presentation.projects.ProjectsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.toshion.motion.presentation.settings.DeveloperOptionsViewModel;
import com.toshion.motion.presentation.settings.DeveloperOptionsViewModel_HiltModules;
import com.toshion.motion.presentation.settings.DeveloperOptionsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.toshion.motion.presentation.settings.DeveloperOptionsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.toshion.motion.presentation.settings.SettingsViewModel;
import com.toshion.motion.presentation.settings.SettingsViewModel_HiltModules;
import com.toshion.motion.presentation.settings.SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.toshion.motion.presentation.settings.SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerToshionMotionApplication_HiltComponents_SingletonC {
  private DaggerToshionMotionApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public ToshionMotionApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements ToshionMotionApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public ToshionMotionApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements ToshionMotionApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public ToshionMotionApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements ToshionMotionApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public ToshionMotionApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements ToshionMotionApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ToshionMotionApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements ToshionMotionApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ToshionMotionApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements ToshionMotionApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public ToshionMotionApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements ToshionMotionApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public ToshionMotionApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends ToshionMotionApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends ToshionMotionApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends ToshionMotionApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends ToshionMotionApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>of(CreateProjectViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CreateProjectViewModel_HiltModules.KeyModule.provide(), DeveloperOptionsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DeveloperOptionsViewModel_HiltModules.KeyModule.provide(), EditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, EditorViewModel_HiltModules.KeyModule.provide(), ProjectsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ProjectsViewModel_HiltModules.KeyModule.provide(), SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SettingsViewModel_HiltModules.KeyModule.provide()));
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends ToshionMotionApplication_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    Provider<CreateProjectViewModel> createProjectViewModelProvider;

    Provider<DeveloperOptionsViewModel> developerOptionsViewModelProvider;

    Provider<EditorViewModel> editorViewModelProvider;

    Provider<ProjectsViewModel> projectsViewModelProvider;

    Provider<SettingsViewModel> settingsViewModelProvider;

    ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    CreateProjectUseCase createProjectUseCase() {
      return new CreateProjectUseCase(singletonCImpl.bindProjectRepositoryProvider.get());
    }

    GetClipsUseCase getClipsUseCase() {
      return new GetClipsUseCase(singletonCImpl.bindClipRepositoryProvider.get());
    }

    AddClipUseCase addClipUseCase() {
      return new AddClipUseCase(singletonCImpl.bindClipRepositoryProvider.get());
    }

    TrimClipUseCase trimClipUseCase() {
      return new TrimClipUseCase(singletonCImpl.bindClipRepositoryProvider.get());
    }

    SplitClipUseCase splitClipUseCase() {
      return new SplitClipUseCase(singletonCImpl.bindClipRepositoryProvider.get());
    }

    DeleteClipUseCase deleteClipUseCase() {
      return new DeleteClipUseCase(singletonCImpl.bindClipRepositoryProvider.get());
    }

    GetProjectsUseCase getProjectsUseCase() {
      return new GetProjectsUseCase(singletonCImpl.bindProjectRepositoryProvider.get());
    }

    DeleteProjectUseCase deleteProjectUseCase() {
      return new DeleteProjectUseCase(singletonCImpl.bindProjectRepositoryProvider.get());
    }

    DuplicateProjectUseCase duplicateProjectUseCase() {
      return new DuplicateProjectUseCase(singletonCImpl.bindProjectRepositoryProvider.get());
    }

    RenameProjectUseCase renameProjectUseCase() {
      return new RenameProjectUseCase(singletonCImpl.bindProjectRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.createProjectViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.developerOptionsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.editorViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.projectsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>of(CreateProjectViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (createProjectViewModelProvider)), DeveloperOptionsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (developerOptionsViewModelProvider)), EditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (editorViewModelProvider)), ProjectsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (projectsViewModelProvider)), SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (settingsViewModelProvider))));
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.toshion.motion.presentation.create.CreateProjectViewModel
          return (T) new CreateProjectViewModel(viewModelCImpl.createProjectUseCase());

          case 1: // com.toshion.motion.presentation.settings.DeveloperOptionsViewModel
          return (T) new DeveloperOptionsViewModel(singletonCImpl.appFileManagerProvider.get());

          case 2: // com.toshion.motion.presentation.editor.EditorViewModel
          return (T) new EditorViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.bindProjectRepositoryProvider.get(), viewModelCImpl.getClipsUseCase(), viewModelCImpl.addClipUseCase(), viewModelCImpl.trimClipUseCase(), viewModelCImpl.splitClipUseCase(), viewModelCImpl.deleteClipUseCase(), viewModelCImpl.savedStateHandle);

          case 3: // com.toshion.motion.presentation.projects.ProjectsViewModel
          return (T) new ProjectsViewModel(viewModelCImpl.getProjectsUseCase(), viewModelCImpl.deleteProjectUseCase(), viewModelCImpl.duplicateProjectUseCase(), viewModelCImpl.renameProjectUseCase());

          case 4: // com.toshion.motion.presentation.settings.SettingsViewModel
          return (T) new SettingsViewModel(singletonCImpl.bindSettingsRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends ToshionMotionApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends ToshionMotionApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends ToshionMotionApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    Provider<ToshionMotionDatabase> provideDatabaseProvider;

    Provider<ProjectRepositoryImpl> projectRepositoryImplProvider;

    Provider<ProjectRepository> bindProjectRepositoryProvider;

    Provider<AppFileManager> appFileManagerProvider;

    Provider<ClipRepositoryImpl> clipRepositoryImplProvider;

    Provider<ClipRepository> bindClipRepositoryProvider;

    Provider<SettingsDataStore> settingsDataStoreProvider;

    Provider<SettingsRepositoryImpl> settingsRepositoryImplProvider;

    Provider<SettingsRepository> bindSettingsRepositoryProvider;

    SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    ProjectDao projectDao() {
      return DatabaseModule_ProvideProjectDaoFactory.provideProjectDao(provideDatabaseProvider.get());
    }

    ClipDao clipDao() {
      return DatabaseModule_ProvideClipDaoFactory.provideClipDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<ToshionMotionDatabase>(singletonCImpl, 1));
      this.projectRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 0);
      this.bindProjectRepositoryProvider = DoubleCheck.provider((Provider) (projectRepositoryImplProvider));
      this.appFileManagerProvider = DoubleCheck.provider(new SwitchingProvider<AppFileManager>(singletonCImpl, 2));
      this.clipRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 3);
      this.bindClipRepositoryProvider = DoubleCheck.provider((Provider) (clipRepositoryImplProvider));
      this.settingsDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<SettingsDataStore>(singletonCImpl, 5));
      this.settingsRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 4);
      this.bindSettingsRepositoryProvider = DoubleCheck.provider((Provider) (settingsRepositoryImplProvider));
    }

    @Override
    public void injectToshionMotionApplication(ToshionMotionApplication toshionMotionApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.toshion.motion.data.repository.ProjectRepositoryImpl
          return (T) new ProjectRepositoryImpl(singletonCImpl.projectDao());

          case 1: // com.toshion.motion.data.local.db.ToshionMotionDatabase
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.toshion.motion.core.files.AppFileManager
          return (T) new AppFileManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.toshion.motion.data.repository.ClipRepositoryImpl
          return (T) new ClipRepositoryImpl(singletonCImpl.clipDao());

          case 4: // com.toshion.motion.data.repository.SettingsRepositoryImpl
          return (T) new SettingsRepositoryImpl(singletonCImpl.settingsDataStoreProvider.get());

          case 5: // com.toshion.motion.data.local.datastore.SettingsDataStore
          return (T) new SettingsDataStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
