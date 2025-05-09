package com.huawei.datatype;

import defpackage.jdy;

/* loaded from: classes3.dex */
public class FitnessUserInfo {
    private static final int DEFAULT_HEIGHT = 170;
    private static final int DEFAULT_WEIGHT = 60;
    private int mHeight = 170;
    private int mWeight = 60;
    private long mTime = 0;

    public int getHeight() {
        return ((Integer) jdy.d(Integer.valueOf(this.mHeight))).intValue();
    }

    public void setHeight(int i) {
        this.mHeight = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWeight() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWeight))).intValue();
    }

    public void setWeight(int i) {
        this.mWeight = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getTime() {
        return ((Long) jdy.d(Long.valueOf(this.mTime))).longValue();
    }

    public void setTime(long j) {
        this.mTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }
}
