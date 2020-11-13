package com.lmy.gridphotolibrary.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;


import com.lmy.gridphotolibrary.glide.Glide;

import java.util.List;


/**
 * @功能:
 * @Creat 2020/3/18 15:23
 * @User Lmy
 * @Compony JinAnChang
 */
public class MyImageAdapter extends PagerAdapter {
    public static final String TAG = MyImageAdapter.class.getSimpleName();
    private List<String> imageUrls;
    private AppCompatActivity activity;

    public MyImageAdapter(List<String> imageUrls, AppCompatActivity activity) {
        this.imageUrls = imageUrls;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = imageUrls.get(position);
        ImageView photoView = new ImageView(activity);
        Glide.with(activity)
                .load(url)
                .into(photoView);
        container.addView(photoView);
        photoView.setOnClickListener(view -> {
            Log.d(TAG, "onClick: ");
//                activity.finish();
        });
        return photoView;
    }

    @Override
    public int getCount() {
        return imageUrls != null ? imageUrls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}