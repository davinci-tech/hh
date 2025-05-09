package com.huawei.android.powerkit.adapter;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class AppCycleUsedInfo implements Parcelable {
    public static final Parcelable.Creator<AppCycleUsedInfo> CREATOR = new Parcelable.Creator<AppCycleUsedInfo>() { // from class: com.huawei.android.powerkit.adapter.AppCycleUsedInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: fE_, reason: merged with bridge method [inline-methods] */
        public AppCycleUsedInfo createFromParcel(Parcel parcel) {
            return new AppCycleUsedInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public AppCycleUsedInfo[] newArray(int i) {
            return new AppCycleUsedInfo[i];
        }
    };
    private int mCycleFgCount;
    private long mCycleFgTotalTime;
    private String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppCycleUsedInfo(String str, long j, int i) {
        this.mName = str;
        this.mCycleFgTotalTime = j;
        this.mCycleFgCount = i;
    }

    public AppCycleUsedInfo(Parcel parcel) {
        this.mCycleFgTotalTime = 0L;
        this.mCycleFgCount = 0;
        this.mName = parcel.readString();
        this.mCycleFgTotalTime = parcel.readLong();
        this.mCycleFgCount = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeLong(this.mCycleFgTotalTime);
        parcel.writeInt(this.mCycleFgCount);
    }

    public String getName() {
        return this.mName;
    }

    public long getCycleFgTotalTime() {
        return this.mCycleFgTotalTime;
    }

    public int getCycleFgCount() {
        return this.mCycleFgCount;
    }
}
