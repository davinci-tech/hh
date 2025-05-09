package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import defpackage.jdy;
import java.util.List;

/* loaded from: classes5.dex */
public class CommonSectionList {
    private static final int DEFAULT_VALUE = -1;
    private List<CommonSectionInfo> sectionStructs;
    private int workoutRecordId = -1;
    private int sectionIndex = -1;

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

    public List<CommonSectionInfo> getSectionInfos() {
        return (List) jdy.d(this.sectionStructs);
    }

    public void setSectionInfos(List<CommonSectionInfo> list) {
        this.sectionStructs = (List) jdy.d(list);
    }

    public String toString() {
        return "CommonSectionList{workoutRecordId=" + this.workoutRecordId + ", sectionIndex=" + this.sectionIndex + ", sectionStructs=" + this.sectionStructs + '}';
    }
}
