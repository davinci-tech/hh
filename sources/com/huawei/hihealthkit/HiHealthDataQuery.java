package com.huawei.hihealthkit;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* loaded from: classes.dex */
public class HiHealthDataQuery implements Parcelable {
    private static final int ARRAY_MAX_SIZE = 65535;
    public static final Parcelable.Creator<HiHealthDataQuery> CREATOR = new Parcelable.Creator<HiHealthDataQuery>() { // from class: com.huawei.hihealthkit.HiHealthDataQuery.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwQ_, reason: merged with bridge method [inline-methods] */
        public HiHealthDataQuery createFromParcel(Parcel parcel) {
            return new HiHealthDataQuery(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HiHealthDataQuery[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new HiHealthDataQuery[i];
        }
    };
    private static final String TAG = "HiHealthDataQuery";
    private long mEndTime;
    private HiHealthDataQueryOption mHiHealthDataQueryOption;
    private int mSampleType;
    private long mStartTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected HiHealthDataQuery(Parcel parcel) {
        this.mSampleType = parcel.readInt();
        this.mStartTime = parcel.readLong();
        this.mEndTime = parcel.readLong();
        this.mHiHealthDataQueryOption = (HiHealthDataQueryOption) parcel.readParcelable(HiHealthDataQueryOption.class.getClassLoader());
    }

    public HiHealthDataQuery() {
    }

    public HiHealthDataQuery(int i, long j, long j2, HiHealthDataQueryOption hiHealthDataQueryOption) {
        this.mSampleType = i;
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mHiHealthDataQueryOption = hiHealthDataQueryOption;
    }

    public int getSampleType() {
        return this.mSampleType;
    }

    public void setSampleType(int i) {
        this.mSampleType = i;
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

    public HiHealthDataQueryOption getHiHealthDataQueryOption() {
        return this.mHiHealthDataQueryOption;
    }

    public void setHiHealthDataQueryOption(HiHealthDataQueryOption hiHealthDataQueryOption) {
        this.mHiHealthDataQueryOption = hiHealthDataQueryOption;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            Log.w(TAG, "writeToParcel dest == null.");
            return;
        }
        parcel.writeInt(this.mSampleType);
        parcel.writeLong(this.mStartTime);
        parcel.writeLong(this.mEndTime);
        parcel.writeParcelable(this.mHiHealthDataQueryOption, i);
    }
}
