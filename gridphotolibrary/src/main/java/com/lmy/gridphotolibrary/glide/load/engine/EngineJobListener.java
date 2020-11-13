package com.lmy.gridphotolibrary.glide.load.engine;

import com.lmy.gridphotolibrary.glide.load.Key;

interface EngineJobListener {

  void onEngineJobComplete(EngineJob<?> engineJob, Key key, EngineResource<?> resource);

  void onEngineJobCancelled(EngineJob<?> engineJob, Key key);
}
