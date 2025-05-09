package com.huawei.hihealth.data.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class TrackSwimSegment implements Serializable {
    private static final long serialVersionUID = -3522506309884350154L;
    private int mDistance;
    private int mDuration;
    private int mLocationIndex;
    private int mPace;
    private int mPullTimes;
    private int mSegmentIndex;
    private int mStrokeType;
    private int mSwolf;

    public int requestSegmentIndex() {
        return this.mSegmentIndex;
    }

    public void saveSegmentIndex(int i) {
        this.mSegmentIndex = i;
    }

    public int requestDistance() {
        return this.mDistance;
    }

    public void saveDistance(int i) {
        this.mDistance = i;
    }

    public int requestDuration() {
        return this.mDuration;
    }

    public void saveDuration(int i) {
        this.mDuration = i;
    }

    public int requestPace() {
        return this.mPace;
    }

    public void savePace(int i) {
        this.mPace = i;
    }

    public int requestPullTimes() {
        return this.mPullTimes;
    }

    public void savePullTimes(int i) {
        this.mPullTimes = i;
    }

    public int requestSwolf() {
        return this.mSwolf;
    }

    public void saveSwolf(int i) {
        this.mSwolf = i;
    }

    public int requestStrokeType() {
        return this.mStrokeType;
    }

    public void saveStrokeType(int i) {
        this.mStrokeType = i;
    }

    public int requestLocationIndex() {
        return this.mLocationIndex;
    }

    public void saveLocationIndex(int i) {
        this.mLocationIndex = i;
    }
}
