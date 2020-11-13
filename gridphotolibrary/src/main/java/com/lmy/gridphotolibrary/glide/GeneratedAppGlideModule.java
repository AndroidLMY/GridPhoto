package com.lmy.gridphotolibrary.glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.lmy.gridphotolibrary.glide.manager.RequestManagerRetriever;
import com.lmy.gridphotolibrary.glide.module.AppGlideModule;
import java.util.Set;

/**
 * Allows {@link AppGlideModule}s to exclude {@link com.lmy.gridphotolibrary.glide.annotation.GlideModule}s to
 * ease the migration from {@link com.lmy.gridphotolibrary.glide.annotation.GlideModule}s to Glide's annotation
 * processing system and optionally provides a {@link
 * com.lmy.gridphotolibrary.glide.manager.RequestManagerRetriever.RequestManagerFactory} impl.
 */
abstract class GeneratedAppGlideModule extends AppGlideModule {
  /** This method can be removed when manifest parsing is no longer supported. */
  @NonNull
  abstract Set<Class<?>> getExcludedModuleClasses();

  @Nullable
  RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
    return null;
  }
}
