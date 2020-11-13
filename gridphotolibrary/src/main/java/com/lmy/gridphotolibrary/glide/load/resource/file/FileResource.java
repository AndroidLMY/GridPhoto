package com.lmy.gridphotolibrary.glide.load.resource.file;

import com.lmy.gridphotolibrary.glide.load.resource.SimpleResource;
import java.io.File;

/** A simple {@link com.lmy.gridphotolibrary.glide.load.engine.Resource} that wraps a {@link File}. */
// Public API.
@SuppressWarnings("WeakerAccess")
public class FileResource extends SimpleResource<File> {
  public FileResource(File file) {
    super(file);
  }
}
