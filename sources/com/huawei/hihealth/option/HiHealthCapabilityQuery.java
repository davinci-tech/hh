package com.huawei.hihealth.option;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* loaded from: classes.dex */
public class HiHealthCapabilityQuery implements Parcelable {
    private static final int ARRAY_MAX_SIZE = 65535;
    public static final Parcelable.Creator<HiHealthCapabilityQuery> CREATOR = new Parcelable.Creator<HiHealthCapabilityQuery>() { // from class: com.huawei.hihealth.option.HiHealthCapabilityQuery.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwH_, reason: merged with bridge method [inline-methods] */
        public HiHealthCapabilityQuery createFromParcel(Parcel parcel) {
            return new HiHealthCapabilityQuery(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HiHealthCapabilityQuery[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new HiHealthCapabilityQuery[i];
        }
    };
    private static final String TAG = "HiHealthCapabilityQuery";
    private long mEndTime;
    private String mOptions;
    private long mStartTime;
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected HiHealthCapabilityQuery(Parcel parcel) {
        this(parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readString());
    }

    public HiHealthCapabilityQuery(int i, long j, long j2, String str) {
        this.mType = i;
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mOptions = str;
    }

    public int getType() {
        return this.mType;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public String getOptions() {
        return this.mOptions;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            Log.w(TAG, "writeToParcel dest is null");
            return;
        }
        parcel.writeInt(this.mType);
        parcel.writeLong(this.mStartTime);
        parcel.writeLong(this.mEndTime);
        parcel.writeString(this.mOptions);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private int f4140a;
        private long b;
        private long c;
        private String e;

        public Builder c(int i) {
            this.f4140a = i;
            return this;
        }

        public Builder b(long j) {
            this.c = j;
            return this;
        }

        public Builder e(long j) {
            this.b = j;
            return this;
        }

        public HiHealthCapabilityQuery a() {
            return new HiHealthCapabilityQuery(this.f4140a, this.c, this.b, this.e);
        }
    }
}
