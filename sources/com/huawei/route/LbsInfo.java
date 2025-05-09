package com.huawei.route;

/* loaded from: classes6.dex */
public class LbsInfo {
    private final double[] mLats;
    private final double[] mLons;
    private final int mRealSize;
    private final long[] mTimes;

    public LbsInfo() {
        this(null, null, null, 0);
    }

    public LbsInfo(double[] dArr, double[] dArr2, long[] jArr, int i) {
        this.mLats = dArr;
        this.mLons = dArr2;
        this.mTimes = jArr;
        this.mRealSize = i;
    }

    public double[] getLats() {
        return this.mLats;
    }

    public double[] getLons() {
        return this.mLons;
    }

    public long[] getTimes() {
        return this.mTimes;
    }

    public int getRealSize() {
        return this.mRealSize;
    }

    public boolean isValid() {
        double[] dArr;
        double[] dArr2;
        long[] jArr;
        return (this.mRealSize <= 1 || (dArr = this.mLats) == null || dArr.length == 0 || (dArr2 = this.mLons) == null || dArr2.length == 0 || (jArr = this.mTimes) == null || jArr.length == 0 || dArr.length != dArr2.length || dArr2.length != jArr.length) ? false : true;
    }

    public String toString() {
        return "LbsInfo{Size=" + this.mLats.length + ", mRealSize=" + this.mRealSize + '}';
    }
}
