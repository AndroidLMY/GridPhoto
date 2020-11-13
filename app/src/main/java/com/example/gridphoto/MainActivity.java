package com.example.gridphoto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lmy.gridphotolibrary.glide.gifdecoder.GifDecoder;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.lmy.gridphotolibrary.bean.GridSelectBean;
import com.lmy.gridphotolibrary.view.GridSelectPhotoView;
import com.lmy.gridphotolibrary.view.GridShowPhotoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridSelectPhotoView gridSelect;
    private GridShowPhotoView gridShowPhotoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridShowPhotoView = findViewById(R.id.grid_show);
        List<GridSelectBean> gridSelectBeans = new ArrayList<>();
        gridSelectBeans.add(new GridSelectBean("http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c87a3add4d52a6059252da61e.jpg", false));
        gridSelectBeans.add(new GridSelectBean("http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c87a3add4d52a6059252da61e.jpg", false));
        gridSelectBeans.add(new GridSelectBean("http://g.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4c87a3add4d52a6059252da61e.jpg", false));
        gridShowPhotoView.setDataList(gridSelectBeans);
        gridSelect = findViewById(R.id.grid_select);
        gridSelect.setAddPhotoClickListener(surplusNumber -> {
            EasyPhotos.createAlbum(this,
                    false, GlideEngine.getInstance())
                    //参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                    .setCount(surplusNumber)
                    .start(101);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            //相机或相册回调
            if (requestCode == 101 || requestCode == 100) {
                //返回对象集合：如果你需要了解图片的宽、高、大小、用户是否选中原图选项等信息，可以用这个
                ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
                for (Photo photo : resultPhotos) {
                    gridSelect.setAddPhoto(new GridSelectBean(photo.path, false));
                }
                return;
            }
        }
    }

}