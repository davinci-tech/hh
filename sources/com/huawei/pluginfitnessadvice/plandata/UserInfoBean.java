package com.huawei.pluginfitnessadvice.plandata;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class UserInfoBean implements Parcelable {
    public static final Parcelable.Creator<UserInfoBean> CREATOR = new Parcelable.Creator<UserInfoBean>() { // from class: com.huawei.pluginfitnessadvice.plandata.UserInfoBean.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: cmt_, reason: merged with bridge method [inline-methods] */
        public UserInfoBean createFromParcel(Parcel parcel) {
            return new UserInfoBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public UserInfoBean[] newArray(int i) {
            return new UserInfoBean[i];
        }
    };

    @SerializedName("age")
    private int mAge;

    @SerializedName("height")
    private int mHeight;

    @SerializedName("monthVolume")
    private int mMonthVolume;

    @SerializedName("pbBeforePlan")
    private int mPbBeforePlan;

    @SerializedName("pb")
    private int mPbTime;

    @SerializedName("pbType")
    private int mPbType;

    @SerializedName("runPower")
    private float mRunPower;

    @SerializedName("sex")
    private int mSex;

    @SerializedName("weight")
    private int mWeight;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserInfoBean() {
    }

    protected UserInfoBean(Parcel parcel) {
        this.mSex = parcel.readInt();
        this.mAge = parcel.readInt();
        this.mHeight = parcel.readInt();
        this.mWeight = parcel.readInt();
        this.mPbType = parcel.readInt();
        this.mPbTime = parcel.readInt();
        this.mMonthVolume = parcel.readInt();
        this.mRunPower = parcel.readFloat();
        this.mPbBeforePlan = parcel.readInt();
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

    public int getWeight() {
        return this.mWeight;
    }

    public void setWeight(int i) {
        this.mWeight = i;
    }

    public int getPbType() {
        return this.mPbType;
    }

    public void setPbType(int i) {
        this.mPbType = i;
    }

    public int getPbTime() {
        return this.mPbTime;
    }

    public void setPbTime(int i) {
        this.mPbTime = i;
    }

    public int getMonthVolume() {
        return this.mMonthVolume;
    }

    public void setMonthVolume(int i) {
        this.mMonthVolume = i;
    }

    public float getRunPower() {
        return this.mRunPower;
    }

    public void setRunPower(float f) {
        this.mRunPower = f;
    }

    public int getPbBeforePlan() {
        return this.mPbBeforePlan;
    }

    public void setPbBeforePlan(int i) {
        this.mPbBeforePlan = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mSex);
        parcel.writeInt(this.mAge);
        parcel.writeInt(this.mHeight);
        parcel.writeInt(this.mWeight);
        parcel.writeInt(this.mPbType);
        parcel.writeInt(this.mPbTime);
        parcel.writeInt(this.mMonthVolume);
        parcel.writeFloat(this.mRunPower);
        parcel.writeInt(this.mPbBeforePlan);
    }
}
