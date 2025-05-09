package com.huawei.pluginfitnessadvice.audio;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;

/* loaded from: classes6.dex */
public class ResPurchaseInfo implements Parcelable {
    public static final Parcelable.Creator<ResPurchaseInfo> CREATOR = new Parcelable.Creator<ResPurchaseInfo>() { // from class: com.huawei.pluginfitnessadvice.audio.ResPurchaseInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: cml_, reason: merged with bridge method [inline-methods] */
        public ResPurchaseInfo createFromParcel(Parcel parcel) {
            return new ResPurchaseInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ResPurchaseInfo[] newArray(int i) {
            return new ResPurchaseInfo[i];
        }
    };

    @SerializedName("availableTimes")
    private long mAvailableTimes;

    @SerializedName("resId")
    private String mResId;

    @SerializedName(HwPayConstant.KEY_VALIDTIME)
    private long mValidTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ResPurchaseInfo() {
    }

    protected ResPurchaseInfo(Parcel parcel) {
        this.mResId = parcel.readString();
        this.mValidTime = parcel.readLong();
        this.mAvailableTimes = parcel.readLong();
    }

    public String getResId() {
        return this.mResId;
    }

    public void setResId(String str) {
        this.mResId = str;
    }

    public long getValidTime() {
        return this.mValidTime;
    }

    public void setValidTime(long j) {
        this.mValidTime = j;
    }

    public long getAvailableTimes() {
        return this.mAvailableTimes;
    }

    public void setAvailableTimes(long j) {
        this.mAvailableTimes = j;
    }

    public String toString() {
        return "ResPurchaseInfo{mResId='" + this.mResId + "', mValidTime=" + this.mValidTime + ", mAvailableTimes=" + this.mAvailableTimes + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mResId);
        parcel.writeLong(this.mValidTime);
        parcel.writeLong(this.mAvailableTimes);
    }
}
