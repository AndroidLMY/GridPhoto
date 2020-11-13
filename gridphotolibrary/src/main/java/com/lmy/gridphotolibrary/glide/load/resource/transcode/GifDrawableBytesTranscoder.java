package com.lmy.gridphotolibrary.glide.load.resource.transcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.lmy.gridphotolibrary.glide.load.Options;
import com.lmy.gridphotolibrary.glide.load.engine.Resource;
import com.lmy.gridphotolibrary.glide.load.resource.bytes.BytesResource;
import com.lmy.gridphotolibrary.glide.load.resource.gif.GifDrawable;
import com.lmy.gridphotolibrary.glide.util.ByteBufferUtil;
import java.nio.ByteBuffer;

/**
 * An {@link com.lmy.gridphotolibrary.glide.load.resource.transcode.ResourceTranscoder} that converts {@link
 * com.lmy.gridphotolibrary.glide.load.resource.gif.GifDrawable} into bytes by obtaining the original bytes of
 * the GIF from the {@link com.lmy.gridphotolibrary.glide.load.resource.gif.GifDrawable}.
 */
public class GifDrawableBytesTranscoder implements ResourceTranscoder<GifDrawable, byte[]> {
  @Nullable
  @Override
  public Resource<byte[]> transcode(
      @NonNull Resource<GifDrawable> toTranscode, @NonNull Options options) {
    GifDrawable gifData = toTranscode.get();
    ByteBuffer byteBuffer = gifData.getBuffer();
    return new BytesResource(ByteBufferUtil.toBytes(byteBuffer));
  }
}
