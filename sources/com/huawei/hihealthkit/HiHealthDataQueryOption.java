package com.huawei.hihealthkit;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.huawei.hihealthkit.data.type.AggregateType;
import com.huawei.hihealthkit.data.type.TimeUnit;

/* loaded from: classes.dex */
public class HiHealthDataQueryOption implements Parcelable {
    private static final int ARRAY_MAX_SIZE = 65535;
    public static final Parcelable.Creator<HiHealthDataQueryOption> CREATOR = new Parcelable.Creator<HiHealthDataQueryOption>() { // from class: com.huawei.hihealthkit.HiHealthDataQueryOption.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwR_, reason: merged with bridge method [inline-methods] */
        public HiHealthDataQueryOption createFromParcel(Parcel parcel) {
            return new HiHealthDataQueryOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HiHealthDataQueryOption[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new HiHealthDataQueryOption[i];
        }
    };
    private static final String TAG = "HiHealthDataQueryOption";
    private AggregateType mAggregateType;
    private String mDeviceUuid;
    private int mGroupUnitSize;
    private TimeUnit mGroupUnitType;
    private int mLimit;
    private int mOffset;
    private int mOrder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected HiHealthDataQueryOption(Parcel parcel) {
        this.mDeviceUuid = "";
        if (parcel == null) {
            Log.w(TAG, "HiHealthDataQueryOption in == null.");
            return;
        }
        this.mLimit = parcel.readInt();
        this.mOffset = parcel.readInt();
        this.mOrder = parcel.readInt();
    }

    public HiHealthDataQueryOption() {
        this.mDeviceUuid = "";
    }

    public HiHealthDataQueryOption(int i, int i2, int i3) {
        this.mDeviceUuid = "";
        this.mLimit = i;
        this.mOffset = i2;
        this.mOrder = i3;
    }

    /* loaded from: classes8.dex */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private TimeUnit f4145a;
        private int b;
        private String c = "";
        private int d;
        private AggregateType e;
        private int f;
        private int h;
    }

    private HiHealthDataQueryOption(a aVar) {
        this.mDeviceUuid = "";
        this.mAggregateType = aVar.e;
        this.mGroupUnitSize = aVar.d;
        this.mGroupUnitType = aVar.f4145a;
        this.mLimit = aVar.b;
        this.mOffset = aVar.f;
        this.mOrder = aVar.h;
        this.mDeviceUuid = aVar.c;
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

    public AggregateType getAggregateType() {
        return this.mAggregateType;
    }

    public int getGroupUnitSize() {
        return this.mGroupUnitSize;
    }

    public TimeUnit getGroupUnitType() {
        return this.mGroupUnitType;
    }

    public String getDeviceUuid() {
        return this.mDeviceUuid;
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
}
