package com.huawei.hwdevice.phoneprocess.mgr.exercise.multisync;

/* loaded from: classes5.dex */
public class PostureExtraData {
    private String mExtraDataName;
    private float mExtraDataRatio;
    private int mExtraDataSize;

    public PostureExtraData(int i, String str) {
        this.mExtraDataRatio = 1.0f;
        this.mExtraDataSize = i;
        this.mExtraDataName = str;
    }

    public PostureExtraData(int i, String str, float f) {
        this.mExtraDataSize = i;
        this.mExtraDataName = str;
        this.mExtraDataRatio = f;
    }

    public int getExtraDataSize() {
        return this.mExtraDataSize;
    }

    public void setExtraDataSize(int i) {
        this.mExtraDataSize = i;
    }

    public String getExtraDataName() {
        return this.mExtraDataName;
    }

    public void setExtraDataName(String str) {
        this.mExtraDataName = str;
    }

    public float getExtraDataRatio() {
        return this.mExtraDataRatio;
    }

    public void setExtraDataRatio(float f) {
        this.mExtraDataRatio = f;
    }

    public String toString() {
        return "PostureExtraData{mExtraDataSize=" + this.mExtraDataSize + ", mExtraDataName='" + this.mExtraDataName + "', mExtraDataRatio=" + this.mExtraDataRatio + '}';
    }
}
