package com.toshion.motion.di;

import com.toshion.motion.data.local.db.ToshionMotionDatabase;
import com.toshion.motion.data.local.db.dao.ClipDao;
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
public final class DatabaseModule_ProvideClipDaoFactory implements Factory<ClipDao> {
  private final Provider<ToshionMotionDatabase> databaseProvider;

  private DatabaseModule_ProvideClipDaoFactory(Provider<ToshionMotionDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ClipDao get() {
    return provideClipDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideClipDaoFactory create(
      Provider<ToshionMotionDatabase> databaseProvider) {
    return new DatabaseModule_ProvideClipDaoFactory(databaseProvider);
  }

  public static ClipDao provideClipDao(ToshionMotionDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideClipDao(database));
  }
}
