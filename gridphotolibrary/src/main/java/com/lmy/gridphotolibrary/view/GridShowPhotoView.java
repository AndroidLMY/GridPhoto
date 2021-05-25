package com.lmy.gridphotolibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lmy.gridphotolibrary.R;
import com.lmy.gridphotolibrary.adapter.GridShowPhotoAdapter;
import com.lmy.gridphotolibrary.bean.GridSelectBean;
import com.lmy.gridphotolibrary.utils.RecyclerViewTieku;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @功能:
 * @Creat 2020/11/13 10:31 AM
 * @Compony 465008238@qq.com
 */


public class GridShowPhotoView extends RelativeLayout {
    View inflate;
    private RecyclerView recyclerview;
    private List<GridSelectBean> fileListBeans = new ArrayList<>();
    GridLayoutManager gridLayoutManager;
    private RecyclerViewTieku myItemDecoration;
    private GridShowPhotoAdapter adapter;
    private Context context;
    private ImageView.ScaleType scaleType = ImageView.ScaleType.FIT_XY;


    private int linNumber;//一行显示几列

    public GridShowPhotoView(Context context) {
        this(context, null);
    }

    public GridShowPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        // 获取属性集合 TypedArray
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GridShowPhotoView);
        linNumber = typedArray.getInteger(R.styleable.GridShowPhotoView_lineNumber, 3);
        // 用完要关闭回收资源，必须的强制性的
        typedArray.recycle();
        inflate = LayoutInflater.from(context).inflate(R.layout.grid_photoview, this, true);
        recyclerview = findViewById(R.id.rv_grid_photoview);
        myItemDecoration = new RecyclerViewTieku.Builder(context).
                setHorizontalSpan(5)
                .setVerticalSpan(5)
                .setColorResource(R.color.white)
                .setShowLastLine(false)//是否显示左后一行的分割线
                .build();
        gridLayoutManager = new GridLayoutManager(context, linNumber);
        initRecyclerview();
    }

    public GridShowPhotoView setImageScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
        return this;
    }

    private void initRecyclerview() {
        if (adapter == null) {
            adapter = new GridShowPhotoAdapter(context, fileListBeans);
            recyclerview.setLayoutManager(gridLayoutManager);
//            recyclerview.addItemDecoration(myItemDecoration);
//                recyclerview.setItemAnimator(new DefaultItemAnimator());
            recyclerview.setAdapter(adapter);
        } else {
            adapter.setImageScaleType(scaleType);
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 设置数据源
     */
    public void setClearDataList(List<GridSelectBean> fileListBean) {
        fileListBeans.clear();
        this.fileListBeans.addAll(fileListBean);
        initRecyclerview();
    }

    /**
     * 清除数据
     */
    public void setClear() {
        this.fileListBeans.clear();
        initRecyclerview();
    }

    /**
     * 设置数据源
     */
    public void setDataList(List<GridSelectBean> fileListBean) {
        this.fileListBeans.addAll(fileListBean);
        initRecyclerview();
    }
}
