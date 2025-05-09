package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import com.huawei.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class TransmitWatchFace<T> {

    @SerializedName("currentProgress")
    private int currentProgress;

    @SerializedName("resultCode")
    private int resultCode;

    @SerializedName("watchFaceInfo")
    private T watchFaceInfo;

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public int getCurrentProgress() {
        return this.currentProgress;
    }

    public void setCurrentProgress(int i) {
        this.currentProgress = i;
    }

    public T getWatchFaceInfo() {
        return this.watchFaceInfo;
    }

    public void setWatchFaceInfo(T t) {
        this.watchFaceInfo = t;
    }
}
