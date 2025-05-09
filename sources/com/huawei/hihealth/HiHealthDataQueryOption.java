package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/* loaded from: classes.dex */
public class HiHealthDataQueryOption implements Parcelable {
    private static final int ARRAY_MAX_SIZE = 65535;
    public static final Parcelable.Creator<HiHealthDataQueryOption> CREATOR = new Parcelable.Creator<HiHealthDataQueryOption>() { // from class: com.huawei.hihealth.HiHealthDataQueryOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buJ_, reason: merged with bridge method [inline-methods] */
        public HiHealthDataQueryOption createFromParcel(Parcel parcel) {
            return new HiHealthDataQueryOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HiHealthDataQueryOption[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new HiHealthDataQueryOption[i];
        }
    };
    private static final String TAG = "HiHealthDataQueryOption";
    private int mLimit;
    private int mOffset;
    private int mOrder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected HiHealthDataQueryOption(Parcel parcel) {
        this.mLimit = parcel.readInt();
        this.mOffset = parcel.readInt();
        this.mOrder = parcel.readInt();
    }

    public HiHealthDataQueryOption() {
    }

    public HiHealthDataQueryOption(int i, int i2, int i3) {
        this.mLimit = i;
        this.mOffset = i2;
        this.mOrder = i3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            Log.w(TAG, "writeToParcel dest == null.");
            return;
        }
        parcel.writeInt(this.mLimit);
        parcel.writeInt(this.mOffset);
        parcel.writeInt(this.mOrder);
    }

    public int getLimit() {
        return this.mLimit;
    }

    public void setLimit(int i) {
        this.mLimit = i;
    }

    public int getOffset() {
        return this.mOffset;
    }

    public void setOffset(int i) {
        this.mOffset = i;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public void setOrder(int i) {
        this.mOrder = i;
    }
}
