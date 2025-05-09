package com.huawei.watchface.mvp.model.datatype;

/* loaded from: classes7.dex */
public class WearStruct {
    private int clockBackgroundColor;
    private String clockColor;
    private String clockResource;
    private int clockType;
    private int clockTypeCapability;
    private long curStyleIndex;
    private String wearPreviewName;
    private long wearStyleId;
    private int wearStyleType;
    private int wearTypeCapability;

    public WearStruct() {
    }

    public int getWearStyleType() {
        return this.wearStyleType;
    }

    public void setWearStyleType(int i) {
        this.wearStyleType = i;
    }

    public int getWearTypeCapability() {
        return this.wearTypeCapability;
    }

    public void setWearTypeCapability(int i) {
        this.wearTypeCapability = i;
    }

    public int getClockTypeCapability() {
        return this.clockTypeCapability;
    }

    public void setClockTypeCapability(int i) {
        this.clockTypeCapability = i;
    }

    public long getCurStyleIndex() {
        return this.curStyleIndex;
    }

    public void setCurStyleIndex(long j) {
        this.curStyleIndex = j;
    }

    public WearStruct(String str) {
        this.wearPreviewName = str;
    }

    public WearStruct(long j, String str) {
        this.curStyleIndex = j;
        this.wearPreviewName = str;
        this.wearStyleId = j;
    }

    public WearStruct(int i, String str, String str2, int i2, int i3, String str3, int i4, int i5, int i6, int i7) {
        this.wearStyleId = i;
        this.wearPreviewName = str;
        this.clockResource = str2;
        this.clockType = i2;
        this.clockBackgroundColor = i3;
        this.clockColor = str3;
        this.wearStyleType = i4;
        this.wearTypeCapability = i5;
        this.clockTypeCapability = i6;
        this.curStyleIndex = i7;
    }

    public long getWearStyleId() {
        return this.wearStyleId;
    }

    public void setWearStyleId(long j) {
        this.wearStyleId = j;
    }

    public String getWearPreviewName() {
        return this.wearPreviewName;
    }

    public void setWearPreviewName(String str) {
        this.wearPreviewName = str;
    }

    public int getClockType() {
        return this.clockType;
    }

    public void setClockType(int i) {
        this.clockType = i;
    }

    public String getClockResource() {
        return this.clockResource;
    }

    public void setClockResource(String str) {
        this.clockResource = str;
    }

    public int getClockBackgroundColor() {
        return this.clockBackgroundColor;
    }

    public void setClockBackgroundColor(int i) {
        this.clockBackgroundColor = i;
    }

    public String getClockColor() {
        return this.clockColor;
    }

    public void setClockColor(String str) {
        this.clockColor = str;
    }

    public String toString() {
        return "WearStruct{wearStyleId=" + this.wearStyleId + ", wearPreviewName='" + this.wearPreviewName + "'}";
    }
}
