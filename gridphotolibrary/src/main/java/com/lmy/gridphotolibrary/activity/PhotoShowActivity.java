package com.lmy.gridphotolibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.lmy.gridphotolibrary.R;
import com.lmy.gridphotolibrary.adapter.MyImageAdapter;
import com.lmy.gridphotolibrary.adapter.PhotoViewPager;
import com.lmy.gridphotolibrary.utils.title_utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @功能: 展示图片情页面
 * @Creat 2020/3/18 14:39
 * @User Lmy
 * @Compony JinAnChang
 */

public class PhotoShowActivity extends AppCompatActivity {
    private static List<String> fileListBeans;
    private static int position;
    private int currentPosition;
    private MyImageAdapter adapter;
    private List<String> Urls = new ArrayList<>();
    private TextView tvIndex;
    private PhotoViewPager viewpager;
    private ImageView ivBack;


    public static void show(Context context, List<String> fileListBeans, int position) {
        context.startActivity(new Intent(context, PhotoShowActivity.class));
        PhotoShowActivity.fileListBeans = fileListBeans;
        PhotoShowActivity.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_video);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        initView();
        initData();
    }

    private void initView() {
        tvIndex = findViewById(R.id.tv_index);
        viewpager = findViewById(R.id.viewpager);
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {
        currentPosition = position;
        Urls.addAll(fileListBeans);
        adapter = new MyImageAdapter(Urls, this);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(currentPosition, false);
        tvIndex.setText(currentPosition + 1 + "/" + Urls.size());
        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                tvIndex.setText(currentPosition + 1 + "/" + Urls.size());
            }
        });
    }
}
