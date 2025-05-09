package com.huawei.health.device.kit.hwwsp.hagrid.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class MiniUserDataInfo implements Parcelable {
    public static final Parcelable.Creator<MiniUserDataInfo> CREATOR = new Parcelable.Creator<MiniUserDataInfo>() { // from class: com.huawei.health.device.kit.hwwsp.hagrid.bean.MiniUserDataInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: Fx_, reason: merged with bridge method [inline-methods] */
        public MiniUserDataInfo createFromParcel(Parcel parcel) {
            return new MiniUserDataInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public MiniUserDataInfo[] newArray(int i) {
            return new MiniUserDataInfo[i];
        }
    };
    private int mAge;
    private int mHeight;
    private String mHuid;
    private int mIsMainId;
    private int mIsOver;
    private int mResis;
    private int mSex;
    private int mTime;
    private double mWeight;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MiniUserDataInfo() {
        this(null);
    }

    private MiniUserDataInfo(Parcel parcel) {
        if (parcel != null) {
            this.mWeight = parcel.readDouble();
            this.mResis = parcel.readInt();
            this.mHuid = parcel.readString();
            this.mSex = parcel.readInt();
            this.mAge = parcel.readInt();
            this.mHeight = parcel.readInt();
            this.mIsOver = parcel.readInt();
            this.mIsMainId = parcel.readInt();
            this.mTime = parcel.readInt();
        }
    }

    public double getWeight() {
        return this.mWeight;
    }

    public void setWeight(double d) {
        this.mWeight = d;
    }

    public int getResis() {
        return this.mResis;
    }

    public void setResis(int i) {
        this.mResis = i;
    }

    public String getHuid() {
        return this.mHuid;
    }

    public void setHuid(String str) {
        this.mHuid = str;
    }

    public int getSex() {
        return this.mSex;
    }

    public void setSex(int i) {
        this.mSex = i;
    }

    public int getAge() {
        return this.mAge;
    }

    public void setAge(int i) {
        this.mAge = i;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public int getIsOver() {
        return this.mIsOver;
    }

    public void setIsOver(int i) {
        this.mIsOver = i;
    }

    public int getIsMainId() {
        return this.mIsMainId;
    }

    public void setIsMainId(int i) {
        this.mIsMainId = i;
    }

    public int getTime() {
        return this.mTime;
    }

    public void setTime(int i) {
        this.mTime = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mWeight);
        parcel.writeInt(this.mResis);
        parcel.writeString(this.mHuid);
        parcel.writeInt(this.mSex);
        parcel.writeInt(this.mAge);
        parcel.writeInt(this.mHeight);
        parcel.writeInt(this.mIsOver);
        parcel.writeInt(this.mIsMainId);
        parcel.writeInt(this.mTime);
    }
}
