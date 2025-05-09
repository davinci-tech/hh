package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiHealthDataQuery implements Parcelable {
    public static final int ARRAY_MAX_SIZE = 65535;
    public static final Parcelable.Creator<HiHealthDataQuery> CREATOR = new Parcelable.Creator<HiHealthDataQuery>() { // from class: com.huawei.hihealth.HiHealthDataQuery.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buI_, reason: merged with bridge method [inline-methods] */
        public HiHealthDataQuery createFromParcel(Parcel parcel) {
            return new HiHealthDataQuery(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
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

    public HiHealthDataQuery() {
    }

    public HiHealthDataQuery(int i, long j, long j2, HiHealthDataQueryOption hiHealthDataQueryOption) {
        this.mSampleType = i;
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mHiHealthDataQueryOption = hiHealthDataQueryOption;
    }

    public HiHealthDataQuery(com.huawei.hihealthkit.HiHealthDataQuery hiHealthDataQuery) {
        if (hiHealthDataQuery == null) {
            throw new NullPointerException("query can't be null!");
        }
        this.mSampleType = hiHealthDataQuery.getSampleType();
        this.mStartTime = hiHealthDataQuery.getStartTime();
        this.mEndTime = hiHealthDataQuery.getEndTime();
        com.huawei.hihealthkit.HiHealthDataQueryOption hiHealthDataQueryOption = hiHealthDataQuery.getHiHealthDataQueryOption();
        if (hiHealthDataQueryOption != null) {
            this.mHiHealthDataQueryOption = new HiHealthDataQueryOption(hiHealthDataQueryOption.getLimit(), hiHealthDataQueryOption.getOffset(), hiHealthDataQueryOption.getOrder());
        }
    }

    protected HiHealthDataQuery(Parcel parcel) {
        this.mSampleType = parcel.readInt();
        this.mStartTime = parcel.readLong();
        this.mEndTime = parcel.readLong();
        this.mHiHealthDataQueryOption = (HiHealthDataQueryOption) parcel.readParcelable(HiHealthDataQueryOption.class.getClassLoader());
    }

    public HiHealthDataQueryOption getHiHealthDataQueryOption() {
        return this.mHiHealthDataQueryOption;
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

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mSampleType);
        parcel.writeLong(this.mStartTime);
        parcel.writeLong(this.mEndTime);
        parcel.writeParcelable(this.mHiHealthDataQueryOption, i);
    }
}
