package com.toshion.motion.di;

import com.toshion.motion.data.local.db.ToshionMotionDatabase;
import com.toshion.motion.data.local.db.dao.ProjectDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideProjectDaoFactory implements Factory<ProjectDao> {
  private final Provider<ToshionMotionDatabase> databaseProvider;

  private DatabaseModule_ProvideProjectDaoFactory(
      Provider<ToshionMotionDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ProjectDao get() {
    return provideProjectDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideProjectDaoFactory create(
      Provider<ToshionMotionDatabase> databaseProvider) {
    return new DatabaseModule_ProvideProjectDaoFactory(databaseProvider);
  }

  public static ProjectDao provideProjectDao(ToshionMotionDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideProjectDao(database));
  }
}
