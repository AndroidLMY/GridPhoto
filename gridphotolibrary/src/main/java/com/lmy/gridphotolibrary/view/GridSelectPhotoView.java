package com.lmy.gridphotolibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lmy.gridphotolibrary.R;
import com.lmy.gridphotolibrary.adapter.GridSelectPhotoAdapter;
import com.lmy.gridphotolibrary.bean.GridSelectBean;
import com.lmy.gridphotolibrary.utils.RecyclerViewTieku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author
 * @功能:
 * @Creat 2020/11/13 10:31 AM
 * @Compony 465008238@qq.com
 */


public class GridSelectPhotoView extends RelativeLayout {
    View inflate;
    private RecyclerView recyclerview;
    private int maxNumber;//最多能添加多少张
    private int linNumber;//一行显示几列
    public static int surplusNumber;//添加一些之后还可以添加多少张
    GridLayoutManager gridLayoutManager;
    private RecyclerViewTieku myItemDecoration;
    private GridSelectPhotoAdapter adapter;
    private Context context;
    private List<GridSelectBean> fileListBeans = new ArrayList<>();

    public GridSelectPhotoView(Context context) {
        this(context, null);
    }

    public GridSelectPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        // 获取属性集合 TypedArray
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GridSelectPhotoView);
        maxNumber = typedArray.getInteger(R.styleable.GridSelectPhotoView_maxNumber, 3);
        linNumber = typedArray.getInteger(R.styleable.GridSelectPhotoView_lineNumber, 3);
        surplusNumber = maxNumber;
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

    private void initRecyclerview() {
        if (adapter == null) {
            adapter = new GridSelectPhotoAdapter(context, fileListBeans,maxNumber);
            adapter.setOnItemClickListener(number -> {
                surplusNumber = number;
                onAddPhotoClickListener.addPhotoVideo(surplusNumber);
            });
            recyclerview.setLayoutManager(gridLayoutManager);
//            recyclerview.addItemDecoration(myItemDecoration);
//                recyclerview.setItemAnimator(new DefaultItemAnimator());
            ((DefaultItemAnimator) recyclerview.getItemAnimator()).setSupportsChangeAnimations(false);
            helper.attachToRecyclerView(recyclerview);
            recyclerview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }


    public List<GridSelectBean> getFileList() {
        return adapter.getFileList();
    }



    /**
     * 设置数据源
     */
    public GridSelectPhotoView setAddPhoto(GridSelectBean fileListBean) {
        this.fileListBeans.add(fileListBean);
        initRecyclerview();
        return this;
    }

    private OnAddPhotoClickListener onAddPhotoClickListener;

    public interface OnAddPhotoClickListener {
        void addPhotoVideo(int surplusNumber);
    }

    /**
     * GriSelectView中还可以添加几张图片
     */
    public void setAddPhotoClickListener(OnAddPhotoClickListener onItemClickListener) {
        this.onAddPhotoClickListener = onItemClickListener;
    }

    ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFrlg = 0;

            /**
             * 这里判断一下长按托转的布局是否属于尾布局 如果属于也不设置拖拽
             */
            if (viewHolder.getItemViewType() != 1) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFrlg = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    dragFrlg = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                }
            }
            return makeMovementFlags(dragFrlg, 0);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //滑动事件  下面注释的代码，滑动后数据和条目错乱，被舍弃
            //得到当拖拽的viewHolder的Position
            int fromPosition = viewHolder.getAdapterPosition();
            //拿到当前拖拽到的item的viewHolder
            if (target.getAdapterPosition() == maxNumber - surplusNumber) {
                return true;
            }
            int toPosition = target.getAdapterPosition();
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    if (toPosition < fileListBeans.size()) {
                        Collections.swap(fileListBeans, i, i + 1);
                    } else {
                        return true;
                    }
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    if (toPosition < fileListBeans.size()) {
                        Collections.swap(fileListBeans, i, i - 1);
                    } else {
                        return true;
                    }
                }
            }
            adapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //侧滑删除可以使用；
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        /**
         * 长按选中Item的时候开始调用
         * 长按高亮
         * @param viewHolder
         * @param actionState
         */
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
//            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
//                viewHolder.itemView.setBackgroundColor(Color.RED);
////                //获取系统震动服务//震动70毫秒
////                Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
////                vib.vibrate(70);
//            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        /**
         * 手指松开的时候还原高亮
         * @param recyclerView
         * @param viewHolder
         */
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            adapter.notifyDataSetChanged();  //完成拖动后刷新适配器，这样拖动后删除就不会错乱
        }
    });
}
