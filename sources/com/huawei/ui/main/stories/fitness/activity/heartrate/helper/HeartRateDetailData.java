package com.huawei.ui.main.stories.fitness.activity.heartrate.helper;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class HeartRateDetailData implements Parcelable {
    public static final Parcelable.Creator<HeartRateDetailData> CREATOR = new Parcelable.Creator<HeartRateDetailData>() { // from class: com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateDetailData.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dsJ_, reason: merged with bridge method [inline-methods] */
        public HeartRateDetailData createFromParcel(Parcel parcel) {
            return new HeartRateDetailData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HeartRateDetailData[] newArray(int i) {
            return new HeartRateDetailData[i];
        }
    };
    private long mTime;
    private int mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HeartRateDetailData() {
    }

    public HeartRateDetailData(Parcel parcel) {
        this.mTime = parcel.readLong();
        this.mValue = parcel.readInt();
    }

    public long getTime() {
        return this.mTime;
    }

    public void setTime(long j) {
        this.mTime = j;
    }

    public int getValue() {
        return this.mValue;
    }

    public void setValue(int i) {
        this.mValue = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeLong(this.mTime);
            parcel.writeInt(this.mValue);
        }
    }
}
