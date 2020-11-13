package com.lmy.gridphotolibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lmy.gridphotolibrary.glide.Glide;
import com.lmy.gridphotolibrary.R;
import com.lmy.gridphotolibrary.activity.PhotoShowActivity;
import com.lmy.gridphotolibrary.activity.VideoShowActivity;
import com.lmy.gridphotolibrary.bean.GridSelectBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @author
 * @功能:
 * @Creat 2020/11/13 10:30 AM
 * @Compony 465008238@qq.com
 */


public class GridSelectPhotoAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<GridSelectBean> fileListBeans;
    private final static int CONTENT_TYPE = 0;//内容布局
    private final static int FOOT_TYPE = 1;//尾布局
    private List<String> photoList;
    private int maxNumber;

    public GridSelectPhotoAdapter(Context context, List<GridSelectBean> fileListBeans, int maxNumber) {
        this.context = context;
        this.fileListBeans = fileListBeans;
        this.maxNumber = maxNumber;
    }

    @Override
    public int getItemViewType(int position) {
        if (fileListBeans.size() != maxNumber) {
            if (FOOT_TYPE != 0 && position == fileListBeans.size()) {
                return FOOT_TYPE;
            } else {
                return CONTENT_TYPE;
            }
        } else {
            return CONTENT_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if (fileListBeans.size() == maxNumber) {
            return fileListBeans.size();
        } else {
            return fileListBeans.size() + 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == CONTENT_TYPE) {
            itemView = LayoutInflater.from(context).inflate(R.layout.grid_photo_item, parent, false);
            return new GridSelectPhotoAdapter.ViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(context).inflate(R.layout.grid_photo_foot_item, parent, false);
            return new GridSelectPhotoAdapter.FootViewHolder(itemView);
        }
    }

    public List<GridSelectBean> getFileList() {
        return fileListBeans;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == CONTENT_TYPE) {
            Glide.with(context).load(fileListBeans.get(position).getFileurl()).into(((ViewHolder) holder).ivCover);
            ((ViewHolder) holder).ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fileListBeans.remove(position);
                    notifyItemRemoved(position); //通知移除item
                    notifyItemRangeChanged(0, fileListBeans.size());
                }
            });

            if (fileListBeans.get(position).isVideo()) {
                ((ViewHolder) holder).ivPlayer.setVisibility(View.VISIBLE);
            } else {
                ((ViewHolder) holder).ivPlayer.setVisibility(View.GONE);
            }
            ((ViewHolder) holder).ivCover.setOnClickListener(v -> {
                if (fileListBeans.get(position).isVideo()) {
                    VideoShowActivity.show(context, fileListBeans.get(position).getFileurl());
                } else {
                    photoList = new ArrayList<>();
                    for (int i = 0; i < fileListBeans.size(); i++) {
                        if (!fileListBeans.get(i).isVideo()) {
                            photoList.add(fileListBeans.get(i).getFileurl());
                        }
                    }
                    PhotoShowActivity.show(context, photoList, getIndex(fileListBeans.get(position).getUuid()));
                }
            });
        } else {
            ((FootViewHolder) holder).ivAddphoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.addPhotoVideo(maxNumber - fileListBeans.size());
                }
            });
        }
    }

    public int getIndex(String fileUUID) {
        int index = 0;
        for (int i = 0; i < fileListBeans.size(); i++) {
            if (fileUUID.equals(fileListBeans.get(i).getUuid())) {
                index = i;
            }
        }
        return index;
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void addPhotoVideo(int number);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCover;
        private ImageView ivPlayer;
        private ImageView ivDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            ivPlayer = itemView.findViewById(R.id.iv_player);
            ivDelete = itemView.findViewById(R.id.iv_delete);

        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivAddphoto;

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAddphoto = itemView.findViewById(R.id.iv_addphoto);
        }
    }
}
