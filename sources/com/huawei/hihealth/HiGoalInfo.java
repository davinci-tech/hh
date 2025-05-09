package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import health.compact.a.HiDateUtil;

/* loaded from: classes.dex */
public class HiGoalInfo implements Parcelable {
    public static final Parcelable.Creator<HiGoalInfo> CREATOR = new Parcelable.Creator<HiGoalInfo>() { // from class: com.huawei.hihealth.HiGoalInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buE_, reason: merged with bridge method [inline-methods] */
        public HiGoalInfo createFromParcel(Parcel parcel) {
            return new HiGoalInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HiGoalInfo[] newArray(int i) {
            return new HiGoalInfo[i];
        }
    };
    private int dealLine;
    private int goalType;
    private int goalUnit;
    private double goalValue;
    private int ownerId;
    private int targetType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiGoalInfo() {
        this.ownerId = 0;
    }

    protected HiGoalInfo(Parcel parcel) {
        this.ownerId = 0;
        this.goalType = parcel.readInt();
        this.goalValue = parcel.readDouble();
        this.goalUnit = parcel.readInt();
        this.ownerId = parcel.readInt();
        this.targetType = parcel.readInt();
        this.dealLine = parcel.readInt();
    }

    public int getGoalType() {
        return this.goalType;
    }

    public void setGoalType(int i) {
        this.goalType = i;
    }

    public double getGoalValue() {
        return this.goalValue;
    }

    public void setGoalValue(double d) {
        this.goalValue = d;
    }

    public int getGoalUnit() {
        return this.goalUnit;
    }

    public void setGoalUnit(int i) {
        this.goalUnit = i;
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(int i) {
        this.ownerId = i;
    }

    public int getTargetType() {
        return this.targetType;
    }

    public void setTargetType(int i) {
        this.targetType = i;
    }

    public int getDealLine() {
        return this.dealLine;
    }

    public void setDealLine(int i) {
        this.dealLine = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.goalType);
        parcel.writeDouble(this.goalValue);
        parcel.writeInt(this.goalUnit);
        parcel.writeInt(this.ownerId);
        parcel.writeInt(this.targetType);
        parcel.writeInt(this.dealLine);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiGoalInfo{goalType=");
        stringBuffer.append(this.goalType);
        stringBuffer.append(", goalValue=").append(this.goalValue);
        stringBuffer.append(", goalUnit=").append(this.goalUnit);
        stringBuffer.append(", ownerId=").append(this.ownerId);
        stringBuffer.append(", targetType=").append(this.targetType);
        stringBuffer.append(", dealLine=").append(HiDateUtil.m(this.dealLine));
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
