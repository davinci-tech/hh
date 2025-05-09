package com.huawei.watchface.videoedit.param;

import com.huawei.watchface.videoedit.utils.SaveResolutionUtils;

/* loaded from: classes9.dex */
public class ResolutionInfo {
    private int fileSize;
    private SaveResolutionUtils.VideoResolution innerDefine;
    private int resolutionSize;
    private int stringIndex;

    public ResolutionInfo(int i, SaveResolutionUtils.VideoResolution videoResolution, int i2) {
        this.resolutionSize = i;
        this.innerDefine = videoResolution;
        this.stringIndex = i2;
    }

    public int getResolutionSize() {
        return this.resolutionSize;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int i) {
        this.fileSize = i;
    }

    public SaveResolutionUtils.VideoResolution getInnerDefine() {
        return this.innerDefine;
    }

    public int getStringIndex() {
        return this.stringIndex;
    }
}
