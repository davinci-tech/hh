package com.huawei.healthcloud.plugintrack.golf.bean;

/* loaded from: classes4.dex */
public class UpdateCbkResult {
    public static final int DOWNLOADING = 1;
    public static final int DOWNLOAD_END = 2;
    public static final int DOWNLOAD_MAX_PROGRESS = 100;
    public static final int DOWNLOAD_NOT_START = 0;
    private int courseId;
    private boolean isBgUpdate;
    private String progress;
    private int status;

    public UpdateCbkResult(String str, int i, boolean z, int i2) {
        this.progress = str;
        this.courseId = i;
        this.isBgUpdate = z;
        this.status = i2;
    }

    public String getProgress() {
        return this.progress;
    }

    public void setProgress(String str) {
        this.progress = str;
    }

    public boolean isBgUpdate() {
        return this.isBgUpdate;
    }

    public void setBgUpdate(boolean z) {
        this.isBgUpdate = z;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }
}
