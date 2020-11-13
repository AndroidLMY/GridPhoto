package com.lmy.gridphotolibrary.bean;


import com.lmy.gridphotolibrary.utils.UUIDUtils;

import java.io.Serializable;

/**
 * @author lmy
 * @功能:
 * @Creat 2020/11/13 11:05 AM
 * @Compony 465008238@qq.com
 */
public class GridSelectBean implements Serializable {
    public String fileurl;
    private String uuid;//用于解决同一张照片 无法区别坐标的问题
    private boolean isVideo;//用于标识是否是 图片 视频 还是添加按钮 0 图片 1 视频 2 添加按钮

    public GridSelectBean(String fileurl, boolean isVideo) {
        this.fileurl = fileurl;
        this.uuid = UUIDUtils.getRandomNumNumber();
        this.isVideo = isVideo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public boolean isVideo() {
        return isVideo;
    }

    public void setVideo(boolean video) {
        isVideo = video;
    }

    @Override
    public String toString() {
        return "PhotoVideoListBean{" +
                "fileurl='" + fileurl + '\'' +
                ", isVideo=" + isVideo +
                '}';
    }
}
