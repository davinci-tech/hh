package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import defpackage.jdy;

/* loaded from: classes5.dex */
public class WorkoutRecordPaceMap {

    @SerializedName("distance")
    private int mDistance;

    @SerializedName("pace")
    private int mPace;

    @SerializedName(HwExerciseConstants.JSON_NAME_UNIT_TYPE)
    private int mUnitType;

    @SerializedName(HwExerciseConstants.JSON_NAME_POINT_INDEX)
    private int mPointIndex = 0;

    @SerializedName(HwExerciseConstants.JSON_NAME_IS_LAST_DISTANCE)
    private boolean mIsLastLessDistance = false;

    @SerializedName(HwExerciseConstants.JSON_NAME_LAST_DISTANCE)
    private int mLastLessDistance = 0;

    public void setDistance(int i) {
        this.mDistance = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDistance() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDistance))).intValue();
    }

    public void setUnitType(int i) {
        this.mUnitType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getUnitType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mUnitType))).intValue();
    }

    public void setPace(int i) {
        this.mPace = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getPace() {
        return ((Integer) jdy.d(Integer.valueOf(this.mPace))).intValue();
    }

    public void setPointIndex(int i) {
        this.mPointIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getPointIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.mPointIndex))).intValue();
    }

    public void setLastLessDistance(int i) {
        this.mLastLessDistance = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getLastLessDistance() {
        return ((Integer) jdy.d(Integer.valueOf(this.mLastLessDistance))).intValue();
    }

    public void setIsLastLessDistance(boolean z) {
        this.mIsLastLessDistance = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isLastLessDistance() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.mIsLastLessDistance))).booleanValue();
    }
}
