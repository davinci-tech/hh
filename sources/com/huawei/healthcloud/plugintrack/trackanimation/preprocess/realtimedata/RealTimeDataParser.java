package com.huawei.healthcloud.plugintrack.trackanimation.preprocess.realtimedata;

import android.util.Pair;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hbe;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class RealTimeDataParser {
    private static final int ERROR_KEY = -1;
    private static final String TAG = "Track_RealTimeDataParser";
    protected Map<Integer, Double> mRealTimeData = null;
    protected MotionPath mMotionPath = null;
    protected long mStartTime = 0;
    protected long mEndTime = 0;
    protected long mTotalTime = 0;
    protected int mTargetSize = 0;
    protected int mTargetIndex = -1;
    protected hbe<Integer, Double> mMaxData = new hbe<>(-1, Double.valueOf(-1.7976931348623157E308d));
    private Pair<Integer, Double> mPrevData = null;
    private Pair<Integer, Double> mNextData = null;
    private Iterator<Map.Entry<Integer, Double>> mIterator = null;

    public abstract boolean init();

    public RealTimeDataParser setStartTime(long j) {
        this.mStartTime = j;
        return this;
    }

    public RealTimeDataParser setEndTime(long j) {
        this.mEndTime = j;
        return this;
    }

    public RealTimeDataParser setTotalTime(long j) {
        this.mTotalTime = j;
        return this;
    }

    public RealTimeDataParser setMotionPath(MotionPath motionPath) {
        this.mMotionPath = motionPath;
        return this;
    }

    public RealTimeDataParser setMaxDataCallBack(hbe<Integer, Double> hbeVar) {
        this.mMaxData = hbeVar;
        return this;
    }

    public int getSize() {
        if (this.mMotionPath.requestLbsDataMap() == null || this.mMotionPath.requestLbsDataMap().size() <= 0) {
            LogUtil.h(TAG, "lbs data is null");
            return -1;
        }
        int size = this.mMotionPath.requestLbsDataMap().size();
        this.mTargetSize = size;
        return size;
    }

    public double getNextData() {
        int i = this.mTargetIndex;
        if (i < 0 || i >= this.mTargetSize) {
            LogUtil.h(TAG, "index is error");
            return -1.0d;
        }
        if (i == 0) {
            initData();
        }
        updateData();
        Pair<Integer, Double> pair = this.mPrevData;
        if (pair == null || this.mNextData == null) {
            LogUtil.h(TAG, "data is null");
            return -1.0d;
        }
        double doubleValue = ((Double) pair.second).doubleValue();
        if (((Integer) this.mPrevData.first).intValue() < ((Integer) this.mNextData.first).intValue()) {
            doubleValue += (((this.mTargetIndex - ((Integer) this.mPrevData.first).intValue()) * 1.0d) / (((Integer) this.mNextData.first).intValue() - ((Integer) this.mPrevData.first).intValue())) * (((Double) this.mNextData.second).doubleValue() - ((Double) this.mPrevData.second).doubleValue());
        }
        this.mTargetIndex++;
        return doubleValue;
    }

    public hbe<Integer, Double> getMaxData() {
        return new hbe<>(this.mMaxData.c(), this.mMaxData.a());
    }

    private void initData() {
        Iterator<Map.Entry<Integer, Double>> it = this.mRealTimeData.entrySet().iterator();
        this.mIterator = it;
        if (it.hasNext()) {
            Map.Entry<Integer, Double> next = this.mIterator.next();
            this.mPrevData = new Pair<>(0, next.getValue());
            this.mNextData = new Pair<>(next.getKey(), next.getValue());
        }
    }

    private void updateData() {
        if (this.mTargetIndex <= ((Integer) this.mNextData.first).intValue()) {
            return;
        }
        this.mPrevData = this.mNextData;
        if (this.mIterator.hasNext()) {
            Map.Entry<Integer, Double> next = this.mIterator.next();
            this.mNextData = new Pair<>(next.getKey(), next.getValue());
        } else {
            this.mNextData = new Pair<>(Integer.valueOf(this.mTargetSize - 1), (Double) this.mPrevData.second);
        }
    }
}
