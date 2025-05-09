package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class AIActionBundle implements Parcelable {
    public static String AI_ACTION_BUNDLE = "AI_ACTION_BUNDLE";
    public static final Parcelable.Creator<AIActionBundle> CREATOR = new Parcelable.Creator<AIActionBundle>() { // from class: com.huawei.pluginfitnessadvice.AIActionBundle.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: clz_, reason: merged with bridge method [inline-methods] */
        public AIActionBundle createFromParcel(Parcel parcel) {
            return new AIActionBundle(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public AIActionBundle[] newArray(int i) {
            return new AIActionBundle[i];
        }
    };
    private String mActionId;
    private String mActionName;
    private int mAiMeasurement;
    private String mBodyName;
    private float mCalorie;
    private String mDescribe;
    private long mDuration;
    private String mPullFrom;
    private String mPullOrder;
    private String mResourceId;
    private String mResourceName;
    private int mTargetType;
    private float mTargetValue;
    private int mTowards;

    public static int coverActionToTrackTarget(int i) {
        if (i == 1) {
            return 0;
        }
        if (i == 10) {
            return 5;
        }
        return i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mActionId);
        parcel.writeString(this.mActionName);
        parcel.writeInt(this.mTargetType);
        parcel.writeFloat(this.mTargetValue);
        parcel.writeInt(this.mAiMeasurement);
        parcel.writeFloat(this.mCalorie);
        parcel.writeLong(this.mDuration);
        parcel.writeInt(this.mTowards);
        parcel.writeString(this.mDescribe);
        parcel.writeString(this.mPullFrom);
        parcel.writeString(this.mResourceId);
        parcel.writeString(this.mResourceName);
        parcel.writeString(this.mBodyName);
        parcel.writeString(this.mPullOrder);
    }

    public void readFromParcel(Parcel parcel) {
        this.mActionId = parcel.readString();
        this.mActionName = parcel.readString();
        this.mTargetType = parcel.readInt();
        this.mTargetValue = parcel.readFloat();
        this.mAiMeasurement = parcel.readInt();
        this.mCalorie = parcel.readFloat();
        this.mDuration = parcel.readLong();
        this.mTowards = parcel.readInt();
        this.mDescribe = parcel.readString();
        this.mPullFrom = parcel.readString();
        this.mResourceId = parcel.readString();
        this.mResourceName = parcel.readString();
        this.mBodyName = parcel.readString();
        this.mPullOrder = parcel.readString();
    }

    public AIActionBundle() {
    }

    protected AIActionBundle(Parcel parcel) {
        this.mActionId = parcel.readString();
        this.mActionName = parcel.readString();
        this.mTargetType = parcel.readInt();
        this.mTargetValue = parcel.readFloat();
        this.mAiMeasurement = parcel.readInt();
        this.mCalorie = parcel.readFloat();
        this.mDuration = parcel.readLong();
        this.mTowards = parcel.readInt();
        this.mDescribe = parcel.readString();
        this.mPullFrom = parcel.readString();
        this.mResourceId = parcel.readString();
        this.mResourceName = parcel.readString();
        this.mBodyName = parcel.readString();
        this.mPullOrder = parcel.readString();
    }

    public static Parcelable.Creator<AIActionBundle> getCREATOR() {
        return CREATOR;
    }

    public String getActionId() {
        return this.mActionId;
    }

    public void setActionId(String str) {
        this.mActionId = str;
    }

    public String getActionName() {
        return this.mActionName;
    }

    public void setActionName(String str) {
        this.mActionName = str;
    }

    public int getTargetType() {
        return this.mTargetType;
    }

    public void setTargetType(int i) {
        this.mTargetType = i;
    }

    public float getTargetValue() {
        return this.mTargetValue;
    }

    public void setTargetValue(float f) {
        this.mTargetValue = f;
    }

    public int getAiMeasurement() {
        return this.mAiMeasurement;
    }

    public void setAiMeasurement(int i) {
        this.mAiMeasurement = i;
    }

    public float getCalorie() {
        return this.mCalorie;
    }

    public void setCalorie(float f) {
        this.mCalorie = f;
    }

    public float getDuration() {
        return this.mDuration;
    }

    public void setDuration(long j) {
        this.mDuration = j;
    }

    public int getTowards() {
        return this.mTowards;
    }

    public void setTowards(int i) {
        this.mTowards = i;
    }

    public String getDescribe() {
        return this.mDescribe;
    }

    public void setDescribe(String str) {
        this.mDescribe = str;
    }

    public String getPullFrom() {
        return this.mPullFrom;
    }

    public void setPullFrom(String str) {
        this.mPullFrom = str;
    }

    public String getResourceId() {
        return this.mResourceId;
    }

    public void setResourceId(String str) {
        this.mResourceId = str;
    }

    public String getResourceName() {
        return this.mResourceName;
    }

    public void setResourceName(String str) {
        this.mResourceName = str;
    }

    public String getBodyName() {
        return this.mBodyName;
    }

    public void setBodyName(String str) {
        this.mBodyName = str;
    }

    public String getPullOrder() {
        return this.mPullOrder;
    }

    public void setPullOrder(String str) {
        this.mPullOrder = str;
    }
}
