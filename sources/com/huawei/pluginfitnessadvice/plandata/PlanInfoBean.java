package com.huawei.pluginfitnessadvice.plandata;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class PlanInfoBean implements Parcelable {
    public static final Parcelable.Creator<PlanInfoBean> CREATOR = new Parcelable.Creator<PlanInfoBean>() { // from class: com.huawei.pluginfitnessadvice.plandata.PlanInfoBean.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: cms_, reason: merged with bridge method [inline-methods] */
        public PlanInfoBean createFromParcel(Parcel parcel) {
            return new PlanInfoBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public PlanInfoBean[] newArray(int i) {
            return new PlanInfoBean[i];
        }
    };

    @SerializedName("endTime")
    private long mEndTime;

    @SerializedName("exeDate")
    private List<Integer> mExeDate;

    @SerializedName("groupType")
    private int mGroupType;

    @SerializedName("forRace")
    private boolean mIsForRace;

    @SerializedName("lang")
    private String mLanguage;

    @SerializedName("planType")
    private int mPlanType;

    @SerializedName("remindTime")
    private int mRemindTime;

    @SerializedName("runDate")
    private List<Integer> mRunDate;

    @SerializedName("startTime")
    private long mStartTime;

    @SerializedName("targetTime")
    private int mTargetTime;

    @SerializedName("timeZone")
    private String mTimeZone;

    @SerializedName("trainLevel")
    private int mTrainLevel;

    @SerializedName("weekCount")
    private int mWeekCount;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlanInfoBean() {
    }

    private PlanInfoBean(Parcel parcel) {
        this.mPlanType = parcel.readInt();
        this.mGroupType = parcel.readInt();
        this.mIsForRace = parcel.readByte() != 0;
        this.mTargetTime = parcel.readInt();
        this.mStartTime = parcel.readLong();
        this.mEndTime = parcel.readLong();
        this.mRunDate = new ArrayList();
        this.mExeDate = new ArrayList();
        parcel.readList(this.mRunDate, Integer.class.getClassLoader());
        parcel.readList(this.mExeDate, Integer.class.getClassLoader());
        this.mRemindTime = parcel.readInt();
        this.mWeekCount = parcel.readInt();
        this.mGroupType = parcel.readInt();
        this.mLanguage = parcel.readString();
        this.mTimeZone = parcel.readString();
        this.mTrainLevel = parcel.readInt();
    }

    public int getPlanType() {
        return this.mPlanType;
    }

    public void setPlanType(int i) {
        this.mPlanType = i;
    }

    public boolean getIsForRace() {
        return this.mIsForRace;
    }

    public void setIsForRace(boolean z) {
        this.mIsForRace = z;
    }

    public int getTargetTime() {
        return this.mTargetTime;
    }

    public void setTargetTime(int i) {
        this.mTargetTime = i;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public List<Integer> getRunDate() {
        return this.mRunDate;
    }

    public void setRunDate(List<Integer> list) {
        this.mRunDate = list;
    }

    public List<Integer> getExeDate() {
        return this.mExeDate;
    }

    public void setExeDate(List<Integer> list) {
        this.mExeDate = list;
    }

    public void setRemindTime(int i) {
        this.mRemindTime = i;
    }

    public int getRemindTime() {
        return this.mRemindTime;
    }

    public void setWeekCount(int i) {
        this.mWeekCount = i;
    }

    public int getWeekCount() {
        return this.mWeekCount;
    }

    public void setGroupType(int i) {
        this.mGroupType = i;
    }

    public int getGroupType() {
        return this.mGroupType;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public void setLanguage(String str) {
        this.mLanguage = str;
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    public void setTimeZone(String str) {
        this.mTimeZone = str;
    }

    public void setTrainLevel(int i) {
        this.mTrainLevel = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mPlanType);
        parcel.writeInt(this.mGroupType);
        parcel.writeByte(this.mIsForRace ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mTargetTime);
        parcel.writeLong(this.mStartTime);
        parcel.writeLong(this.mEndTime);
        parcel.writeList(this.mRunDate);
        parcel.writeList(this.mExeDate);
        parcel.writeInt(this.mRemindTime);
        parcel.writeInt(this.mWeekCount);
        parcel.writeInt(this.mGroupType);
        parcel.writeString(this.mLanguage);
        parcel.writeString(this.mTimeZone);
        parcel.writeInt(this.mTrainLevel);
    }
}
