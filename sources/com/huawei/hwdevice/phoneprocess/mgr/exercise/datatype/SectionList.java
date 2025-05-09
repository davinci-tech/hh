package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import defpackage.jdy;
import java.util.List;

/* loaded from: classes5.dex */
public class SectionList {

    @SerializedName("section_index")
    private int sectionIndex;

    @SerializedName(HwExerciseConstants.JSON_NAME_SECTION_STRUCT)
    private List<SectionInfo> sectionStructs;

    @SerializedName(HwExerciseConstants.JSON_NAME_WORKOUT_RECORD_ID)
    private int workoutRecordId;

    public int getWorkoutRecordId() {
        return ((Integer) jdy.d(Integer.valueOf(this.workoutRecordId))).intValue();
    }

    public void setWorkoutRecordId(int i) {
        this.workoutRecordId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSectionIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.sectionIndex))).intValue();
    }

    public void setSectionIndex(int i) {
        this.sectionIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public List<SectionInfo> getSectionInfos() {
        return (List) jdy.d(this.sectionStructs);
    }

    public void setSectionInfos(List<SectionInfo> list) {
        this.sectionStructs = (List) jdy.d(list);
    }
}
