package com.huawei.ui.homehealth.runcard.operation.recommendalgo.model;

import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.List;

/* loaded from: classes9.dex */
public class RecommendSchedule {
    private static final String TAG = "Track_RecommendSchedule";
    private int mCurrentWorkout;
    private int mLastWorkout;
    private List<RecommendWorkout> mRecommendWorkouts;
    private int mRestWeight;
    private int mWeightSum;

    public RecommendSchedule(int i, List<RecommendWorkout> list) {
        this.mWeightSum = i;
        this.mRestWeight = i;
        this.mRecommendWorkouts = list;
        if (list == null) {
            LogUtil.h(TAG, "works is null");
        } else {
            this.mCurrentWorkout = 0;
            this.mLastWorkout = -1;
        }
    }

    private void resetRecommendStatus() {
        LogUtil.a(TAG, "resetRecommendStatus");
        this.mRestWeight = this.mWeightSum;
        this.mCurrentWorkout = 0;
        List<RecommendWorkout> list = this.mRecommendWorkouts;
        if (list != null) {
            for (RecommendWorkout recommendWorkout : list) {
                if (recommendWorkout != null) {
                    recommendWorkout.resetWeight();
                }
            }
        }
    }

    public void resetSchedule() {
        this.mLastWorkout = -1;
        resetRecommendStatus();
    }

    public List<RecommendWorkout> acquireRecommendWorkouts() {
        return this.mRecommendWorkouts;
    }

    public RecommendWorkout acquireLastRecommend() {
        int i = this.mLastWorkout;
        if (i == -1) {
            if (koq.d(this.mRecommendWorkouts, this.mCurrentWorkout)) {
                return this.mRecommendWorkouts.get(this.mCurrentWorkout);
            }
            return null;
        }
        if (koq.d(this.mRecommendWorkouts, i)) {
            return this.mRecommendWorkouts.get(this.mLastWorkout);
        }
        return null;
    }

    public RecommendWorkout getRecommendWorkout() {
        if (koq.b(this.mRecommendWorkouts, this.mCurrentWorkout)) {
            return null;
        }
        RecommendWorkout recommendWorkout = this.mRecommendWorkouts.get(this.mCurrentWorkout);
        int i = 1;
        recommendWorkout.saveRestWeight(recommendWorkout.acquireRestWeight() - 1);
        int i2 = this.mRestWeight - 1;
        this.mRestWeight = i2;
        this.mLastWorkout = this.mCurrentWorkout;
        if (i2 == 0) {
            resetRecommendStatus();
        }
        int i3 = this.mLastWorkout;
        int size = this.mRecommendWorkouts.size();
        while (true) {
            if (i < size) {
                int i4 = (i3 + i) % size;
                RecommendWorkout recommendWorkout2 = this.mRecommendWorkouts.get(i4);
                if (recommendWorkout2 != null && recommendWorkout2.acquireRestWeight() > 0) {
                    this.mCurrentWorkout = i4;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (this.mCurrentWorkout == i3) {
            resetRecommendStatus();
        }
        return recommendWorkout;
    }
}
