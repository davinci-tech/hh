package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.jdy;
import java.util.List;

/* loaded from: classes5.dex */
public class WorkoutRecord {

    @SerializedName("workout_record_count")
    private int workoutRecordCount;
    private List<WorkoutRecordStruct> workoutRecordStructList;

    public int getWorkoutRecordCount() {
        return ((Integer) jdy.d(Integer.valueOf(this.workoutRecordCount))).intValue();
    }

    public void setWorkoutRecordCount(int i) {
        this.workoutRecordCount = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public List<WorkoutRecordStruct> getWorkoutRecordStructList() {
        return (List) jdy.d(this.workoutRecordStructList);
    }

    public void setWorkoutRecordStructList(List<WorkoutRecordStruct> list) {
        this.workoutRecordStructList = (List) jdy.d(list);
    }
}
