package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;

/* loaded from: classes3.dex */
public class DayTotalRecord implements Parcelable {
    public static final Parcelable.Creator<DayTotalRecord> CREATOR = new Parcelable.Creator<DayTotalRecord>() { // from class: com.huawei.basefitnessadvice.model.DayTotalRecord.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DayTotalRecord createFromParcel(Parcel parcel) {
            return new DayTotalRecord(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DayTotalRecord[] newArray(int i) {
            return new DayTotalRecord[i];
        }
    };
    private static final String TAG = "Suggestion_DayTotalRecord";

    @SerializedName("calorie")
    private float mCalorie;

    @SerializedName("date")
    private int mDate;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("id")
    private int mId;

    @SerializedName("isLastRecord")
    private int mLastRecord;

    @SerializedName("status")
    private int mStatus;

    @SerializedName(UserCloseRecord.TIME_STAMP)
    private long mTimestamp;

    @SerializedName("totalCalorie")
    private float mTotalCalorie;

    @SerializedName("totalDuration")
    private int mTotalDuration;

    @SerializedName(JsbMapKeyNames.H5_USER_ID)
    private String mUserId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DayTotalRecord() {
    }

    protected DayTotalRecord(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mUserId = parcel.readString();
        this.mDate = parcel.readInt();
        this.mTimestamp = parcel.readLong();
        this.mCalorie = parcel.readFloat();
        this.mDuration = parcel.readInt();
        this.mStatus = parcel.readInt();
        this.mTotalCalorie = parcel.readFloat();
        this.mTotalDuration = parcel.readInt();
        this.mLastRecord = parcel.readInt();
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public int getDate() {
        return this.mDate;
    }

    public void setDate(int i) {
        this.mDate = i;
    }

    public long getTimeStamp() {
        return this.mTimestamp;
    }

    public void setTimeStamp(long j) {
        this.mTimestamp = j;
    }

    public float getCalorie() {
        return this.mCalorie;
    }

    public void setCalorie(float f) {
        this.mCalorie = f;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public float getTotalCalorie() {
        return this.mTotalCalorie;
    }

    public void setTotalCalorie(float f) {
        this.mTotalCalorie = f;
    }

    public int getTotalDuration() {
        return this.mTotalDuration;
    }

    public void setTotalDuration(int i) {
        this.mTotalDuration = i;
    }

    public int getIsLastRecord() {
        return this.mLastRecord;
    }

    public void setIsLastRecord(int i) {
        this.mLastRecord = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeInt(this.mId);
        parcel.writeString(this.mUserId);
        parcel.writeInt(this.mDate);
        parcel.writeLong(this.mTimestamp);
        parcel.writeFloat(this.mCalorie);
        parcel.writeInt(this.mDuration);
        parcel.writeInt(this.mStatus);
        parcel.writeFloat(this.mTotalCalorie);
        parcel.writeInt(this.mTotalDuration);
        parcel.writeInt(this.mLastRecord);
    }

    public String getUserId() {
        return this.mUserId;
    }

    public void setUserId(String str) {
        this.mUserId = str;
    }
}
