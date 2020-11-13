package com.lmy.gridphotolibrary.view;

/**
 * @功能: 自定义VideoView 实现全屏
 * @User Lmy
 * @Creat 2020/8/26 22:47
 * @Compony 永远相信美好的事情即将发生
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class CustomerVideoView extends VideoView {

    public CustomerVideoView(Context context) {
        super(context);
    }

    public CustomerVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}