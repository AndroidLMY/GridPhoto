package com.lmy.gridphotolibrary.glide.load.resource.file;

import androidx.annotation.NonNull;
import com.lmy.gridphotolibrary.glide.load.Options;
import com.lmy.gridphotolibrary.glide.load.ResourceDecoder;
import com.lmy.gridphotolibrary.glide.load.engine.Resource;
import java.io.File;

/**
 * A simple {@link com.lmy.gridphotolibrary.glide.load.ResourceDecoder} that creates resource for a given {@link
 * File}.
 */
public class FileDecoder implements ResourceDecoder<File, File> {

  @Override
  public boolean handles(@NonNull File source, @NonNull Options options) {
    return true;
  }

  @Override
  public Resource<File> decode(
      @NonNull File source, int width, int height, @NonNull Options options) {
    return new FileResource(source);
  }
}
