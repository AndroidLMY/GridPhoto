package com.lmy.gridphotolibrary.glide.load.engine;

import com.lmy.gridphotolibrary.glide.load.Key;
import com.lmy.gridphotolibrary.glide.load.Options;
import com.lmy.gridphotolibrary.glide.load.Transformation;
import java.util.Map;

class EngineKeyFactory {

  @SuppressWarnings("rawtypes")
  EngineKey buildKey(
      Object model,
      Key signature,
      int width,
      int height,
      Map<Class<?>, Transformation<?>> transformations,
      Class<?> resourceClass,
      Class<?> transcodeClass,
      Options options) {
    return new EngineKey(
        model, signature, width, height, transformations, resourceClass, transcodeClass, options);
  }
}
