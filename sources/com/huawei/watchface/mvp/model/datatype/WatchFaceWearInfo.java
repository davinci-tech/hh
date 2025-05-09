package com.huawei.watchface.mvp.model.datatype;

import java.util.ArrayList;

/* loaded from: classes7.dex */
public class WatchFaceWearInfo {
    private long clockTypeCapability;
    private long curStyleIndex;
    private int maxStyleNum;
    private int rectRadius;
    private int styleCount;
    private int wearImageOption;
    private int wearStyleType;
    private long wearTypeCapability;
    private int tintClockOption = 1;
    private ArrayList<WearStruct> mWearList = new ArrayList<>();

    public int getRectRadius() {
        return this.rectRadius;
    }

    public void setRectRadius(int i) {
        this.rectRadius = i;
    }

    public int getMaxStyleNum() {
        return this.maxStyleNum;
    }

    public void setMaxStyleNum(int i) {
        this.maxStyleNum = i;
    }

    public int getStyleCount() {
        return this.styleCount;
    }

    public void setStyleCount(int i) {
        this.styleCount = i;
    }

    public int getWearStyleType() {
        return this.wearStyleType;
    }

    public void setWearStyleType(int i) {
        this.wearStyleType = i;
    }

    public long getWearTypeCapability() {
        return this.wearTypeCapability;
    }

    public void setWearTypeCapability(long j) {
        this.wearTypeCapability = j;
    }

    public long getCurStyleIndex() {
        return this.curStyleIndex;
    }

    public void setCurStyleIndex(long j) {
        this.curStyleIndex = j;
    }

    public long getClockTypeCapability() {
        return this.clockTypeCapability;
    }

    public void setClockTypeCapability(long j) {
        this.clockTypeCapability = j;
    }

    public int getWearImageOption() {
        return this.wearImageOption;
    }

    public void setWearImageOption(int i) {
        this.wearImageOption = i;
    }

    public int getTintClockOption() {
        return this.tintClockOption;
    }

    public void setTintClockOption(int i) {
        this.tintClockOption = i;
    }

    public ArrayList<WearStruct> getWearList() {
        return this.mWearList;
    }

    public void setWearList(ArrayList<WearStruct> arrayList) {
        this.mWearList = arrayList;
    }
}
