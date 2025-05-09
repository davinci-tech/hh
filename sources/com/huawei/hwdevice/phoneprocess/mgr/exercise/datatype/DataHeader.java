package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import defpackage.jdy;
import java.util.List;

/* loaded from: classes5.dex */
public class DataHeader {
    private int bitmap;
    private List<String> capacityBitMaps;
    private int dataLength;
    private int dataNumber;
    private int frameId;
    private int sportId;
    private long time;
    private int timeInterval;
    private List<WorkoutDataInfo> workoutDataInfoLists;

    public int getSportId() {
        return ((Integer) jdy.d(Integer.valueOf(this.sportId))).intValue();
    }

    public void setSportId(int i) {
        this.sportId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getFrameId() {
        return ((Integer) jdy.d(Integer.valueOf(this.frameId))).intValue();
    }

    public void setFrameId(int i) {
        this.frameId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getTime() {
        return ((Long) jdy.d(Long.valueOf(this.time))).longValue();
    }

    public void setTime(long j) {
        this.time = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int getTimeInterval() {
        return ((Integer) jdy.d(Integer.valueOf(this.timeInterval))).intValue();
    }

    public void setTimeInterval(int i) {
        this.timeInterval = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public List<WorkoutDataInfo> getWorkoutDataInfoList() {
        return (List) jdy.d(this.workoutDataInfoLists);
    }

    public void setWorkoutDataInfoList(List<WorkoutDataInfo> list) {
        this.workoutDataInfoLists = (List) jdy.d(list);
    }

    public List<String> getBitMap() {
        return (List) jdy.d(this.capacityBitMaps);
    }

    public void setBitMap(List<String> list) {
        this.capacityBitMaps = (List) jdy.d(list);
    }

    public int getDataLength() {
        return this.dataLength;
    }

    public void setDataLength(int i) {
        this.dataLength = i;
    }

    public int getDataNumber() {
        return this.dataNumber;
    }

    public void setDataNumber(int i) {
        this.dataNumber = i;
    }

    public int getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(int i) {
        this.bitmap = i;
    }
}
