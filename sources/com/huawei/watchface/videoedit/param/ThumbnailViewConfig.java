package com.huawei.watchface.videoedit.param;

/* loaded from: classes9.dex */
public class ThumbnailViewConfig {
    private int duration;
    private String filePath = "";
    private int height;
    private int width;

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public void setVideoWidth(int i) {
        this.width = i;
    }

    public int getVideoWidth() {
        return this.width;
    }

    public void setVideoHeight(int i) {
        this.height = i;
    }

    public int getVideoHeight() {
        return this.height;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }
}
