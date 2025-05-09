package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class BaseRecord extends Attribute {
    public static final Parcelable.Creator<BaseRecord> CREATOR = new Parcelable.Creator<BaseRecord>() { // from class: com.huawei.pluginfitnessadvice.BaseRecord.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: clD_, reason: merged with bridge method [inline-methods] */
        public BaseRecord createFromParcel(Parcel parcel) {
            return new BaseRecord(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public BaseRecord[] newArray(int i) {
            return new BaseRecord[i];
        }
    };

    @SerializedName("createTime")
    private long mCreateTime;

    @SerializedName("modifiedTime")
    private long mModifiedTime;

    public BaseRecord() {
    }

    public BaseRecord(Parcel parcel) {
        super(parcel);
        if (parcel != null) {
            this.mCreateTime = parcel.readLong();
            this.mModifiedTime = parcel.readLong();
        }
    }

    @Override // com.huawei.pluginfitnessadvice.Attribute, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        if (parcel != null) {
            parcel.writeLong(this.mCreateTime);
            parcel.writeLong(this.mModifiedTime);
        }
    }

    public long getCreateTime() {
        return this.mCreateTime;
    }

    public void setCreateTime(long j) {
        this.mCreateTime = j;
    }

    public long getModifiedTime() {
        return this.mModifiedTime;
    }

    public void setModifiedTime(long j) {
        this.mModifiedTime = j;
    }
}
