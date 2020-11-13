package com.lmy.gridphotolibrary.glide.load.model.stream;

import androidx.annotation.NonNull;
import com.lmy.gridphotolibrary.glide.load.Options;
import com.lmy.gridphotolibrary.glide.load.model.GlideUrl;
import com.lmy.gridphotolibrary.glide.load.model.ModelLoader;
import com.lmy.gridphotolibrary.glide.load.model.ModelLoaderFactory;
import com.lmy.gridphotolibrary.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;
import java.net.URL;

/**
 * A wrapper class that translates {@link URL} objects into {@link
 * com.lmy.gridphotolibrary.glide.load.model.GlideUrl} objects and then uses the wrapped {@link
 * com.lmy.gridphotolibrary.glide.load.model.ModelLoader} for {@link com.lmy.gridphotolibrary.glide.load.model.GlideUrl}s to
 * load the data.
 */
public class UrlLoader implements ModelLoader<URL, InputStream> {
  private final ModelLoader<GlideUrl, InputStream> glideUrlLoader;

  // Public API.
  @SuppressWarnings("WeakerAccess")
  public UrlLoader(ModelLoader<GlideUrl, InputStream> glideUrlLoader) {
    this.glideUrlLoader = glideUrlLoader;
  }

  @Override
  public LoadData<InputStream> buildLoadData(
      @NonNull URL model, int width, int height, @NonNull Options options) {
    return glideUrlLoader.buildLoadData(new GlideUrl(model), width, height, options);
  }

  @Override
  public boolean handles(@NonNull URL model) {
    return true;
  }

  /** Factory for loading {@link InputStream}s from {@link URL}s. */
  public static class StreamFactory implements ModelLoaderFactory<URL, InputStream> {

    @NonNull
    @Override
    public ModelLoader<URL, InputStream> build(MultiModelLoaderFactory multiFactory) {
      return new UrlLoader(multiFactory.build(GlideUrl.class, InputStream.class));
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }
}
