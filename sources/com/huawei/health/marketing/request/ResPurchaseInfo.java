package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class ResPurchaseInfo implements Parcelable {
    public static final Parcelable.Creator<ResPurchaseInfo> CREATOR = new Parcelable.Creator<ResPurchaseInfo>() { // from class: com.huawei.health.marketing.request.ResPurchaseInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResPurchaseInfo createFromParcel(Parcel parcel) {
            return new ResPurchaseInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResPurchaseInfo[] newArray(int i) {
            return new ResPurchaseInfo[i];
        }
    };
    private long availableTimes;
    private String resId;
    private long validTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ResPurchaseInfo(Parcel parcel) {
        this.resId = parcel.readString();
        this.validTime = parcel.readLong();
        this.availableTimes = parcel.readLong();
    }

    public String getResId() {
        return this.resId;
    }

    public void setResId(String str) {
        this.resId = str;
    }

    public long getValidTime() {
        return this.validTime;
    }

    public void setValidTime(long j) {
        this.validTime = j;
    }

    public long getAvailableTimes() {
        return this.availableTimes;
    }

    public void setAvailableTimes(long j) {
        this.availableTimes = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.resId);
        parcel.writeLong(this.validTime);
        parcel.writeLong(this.availableTimes);
    }
}
