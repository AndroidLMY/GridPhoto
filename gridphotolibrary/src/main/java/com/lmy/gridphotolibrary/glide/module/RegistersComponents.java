package com.lmy.gridphotolibrary.glide.module;

import android.content.Context;
import androidx.annotation.NonNull;
import com.lmy.gridphotolibrary.glide.Glide;
import com.lmy.gridphotolibrary.glide.Registry;

/** An internal interface, to be removed when {@link GlideModule}s are removed. */
// Used only in javadocs.
@SuppressWarnings("deprecation")
@Deprecated
interface RegistersComponents {

  /**
   * Lazily register components immediately after the Glide singleton is created but before any
   * requests can be started.
   *
   * <p>This method will be called once and only once per implementation.
   *
   * @param context An Application {@link Context}.
   * @param glide The Glide singleton that is in the process of being initialized.
   * @param registry An {@link com.lmy.gridphotolibrary.glide.Registry} to use to register components.
   */
  void registerComponents(
          @NonNull Context context, @NonNull Glide glide, @NonNull Registry registry);
}
