package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import defpackage.jdy;
import java.util.List;

/* loaded from: classes5.dex */
public class WorkRecordIndexPaceMapList {
    private int paceIndex = -1;
    List<WorkoutRecordPaceMap> paceMapList;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)
    private int workoutRecordId;

    public void setWorkoutRecordId(int i) {
        this.workoutRecordId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutRecordId() {
        return ((Integer) jdy.d(Integer.valueOf(this.workoutRecordId))).intValue();
    }

    public void setPaceIndex(int i) {
        this.paceIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getPaceIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.paceIndex))).intValue();
    }

    public void setPaceMapList(List<WorkoutRecordPaceMap> list) {
        this.paceMapList = (List) jdy.d(list);
    }

    public List<WorkoutRecordPaceMap> getPaceMapList() {
        return (List) jdy.d(this.paceMapList);
    }
}
