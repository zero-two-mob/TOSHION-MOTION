package com.toshion.motion.data.repository;

import com.toshion.motion.data.local.db.dao.ClipDao;
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
public final class ClipRepositoryImpl_Factory implements Factory<ClipRepositoryImpl> {
  private final Provider<ClipDao> clipDaoProvider;

  private ClipRepositoryImpl_Factory(Provider<ClipDao> clipDaoProvider) {
    this.clipDaoProvider = clipDaoProvider;
  }

  @Override
  public ClipRepositoryImpl get() {
    return newInstance(clipDaoProvider.get());
  }

  public static ClipRepositoryImpl_Factory create(Provider<ClipDao> clipDaoProvider) {
    return new ClipRepositoryImpl_Factory(clipDaoProvider);
  }

  public static ClipRepositoryImpl newInstance(ClipDao clipDao) {
    return new ClipRepositoryImpl(clipDao);
  }
}
