package com.huawei.hihealth.data.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PPGTypeRstData {

    @SerializedName("arrayHrLen")
    private int arrayHrLen;

    @SerializedName("arrayOutsideRr")
    private ArrayList<Double> arrayOutsideRr;

    @SerializedName("arrayTypeIdx")
    private ArrayList<Integer> arrayTypeIdx;

    @SerializedName("points")
    private ArrayList<PPGTypeRstPointClone> points;

    @SerializedName("selectFlag")
    private byte selectFlag;

    @SerializedName(UserCloseRecord.TIME_STAMP)
    private long timeStamp;

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public ArrayList<Integer> getArrayTypeIdx() {
        return this.arrayTypeIdx;
    }

    public void setArrayTypeIdx(ArrayList<Integer> arrayList) {
        this.arrayTypeIdx = arrayList;
    }

    public int getArrayHrLen() {
        return this.arrayHrLen;
    }

    public void setArrayHrLen(int i) {
        this.arrayHrLen = i;
    }

    public ArrayList<Double> getArrayOutsideRr() {
        return this.arrayOutsideRr;
    }

    public void setArrayOutsideRr(ArrayList<Double> arrayList) {
        this.arrayOutsideRr = arrayList;
    }

    public byte getSelectFlag() {
        return this.selectFlag;
    }

    public void setSelectFlag(byte b) {
        this.selectFlag = b;
    }

    public ArrayList<PPGTypeRstPointClone> getPoints() {
        return this.points;
    }

    public void setPoints(ArrayList<PPGTypeRstPointClone> arrayList) {
        this.points = arrayList;
    }
}
