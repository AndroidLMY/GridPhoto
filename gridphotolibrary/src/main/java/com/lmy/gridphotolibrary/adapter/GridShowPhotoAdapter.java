package com.lmy.gridphotolibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.lmy.gridphotolibrary.R;
import com.lmy.gridphotolibrary.activity.PhotoShowActivity;
import com.lmy.gridphotolibrary.activity.VideoShowActivity;
import com.lmy.gridphotolibrary.bean.GridSelectBean;
import com.lmy.gridphotolibrary.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * @功能:
 * @Creat 2020/3/18 11:42
 * @User Lmy
 * @Compony JinAnChang
 */
public class GridShowPhotoAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<GridSelectBean> fileListBeans;
    private List<String> photoList;
    private List<String> uuid;

    public GridShowPhotoAdapter(Context context, List<GridSelectBean> fileListBeans) {
        this.context = context;
        this.fileListBeans = fileListBeans;
    }

    @Override
    public int getItemCount() {
        return fileListBeans.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.grid_photo_item, parent, false);
        return new GridShowPhotoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Glide.with(context).load(fileListBeans.get(position).getFileurl()).into(((ViewHolder) holder).ivCover);

        if (fileListBeans.get(position).isVideo()) {
            ((ViewHolder) holder).ivPlayer.setVisibility(View.VISIBLE);
        } else {
            ((ViewHolder) holder).ivPlayer.setVisibility(View.GONE);
        }
        ((ViewHolder) holder).ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileListBeans.get(position).isVideo()) {
                    VideoShowActivity.show(context, fileListBeans.get(position).getFileurl());
                } else {
                    photoList = new ArrayList<>();
                    uuid = new ArrayList<>();
                    for (int i = 0; i < fileListBeans.size(); i++) {
                        if (!fileListBeans.get(i).isVideo()) {
                            photoList.add(fileListBeans.get(i).getFileurl());
                            uuid.add(fileListBeans.get(i).getUuid());
                        }
                    }
                    PhotoShowActivity.show(context, photoList, getIndex(fileListBeans.get(position).getUuid()));
                }
            }
        });
    }

    public int getIndex(String file) {
        int index = 0;
        for (int i = 0; i < uuid.size(); i++) {
            if (file.equals(uuid.get(i))) {
                index = i;
            }
        }
        return index;
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
            ivDelete.setVisibility(View.GONE);
        }
    }







}
