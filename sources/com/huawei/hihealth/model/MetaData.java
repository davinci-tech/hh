package com.huawei.hihealth.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class MetaData implements Parcelable {
    public static final Parcelable.Creator<MetaData> CREATOR = new Parcelable.Creator<MetaData>() { // from class: com.huawei.hihealth.model.MetaData.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwv_, reason: merged with bridge method [inline-methods] */
        public MetaData createFromParcel(Parcel parcel) {
            return new MetaData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public MetaData[] newArray(int i) {
            return new MetaData[i];
        }
    };
    private String metaKey;
    private String metaValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MetaData(String str, String str2) {
        this.metaKey = str;
        this.metaValue = str2;
    }

    protected MetaData(Parcel parcel) {
        this.metaKey = parcel.readString();
        this.metaValue = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.metaKey);
        parcel.writeString(this.metaValue);
    }

    public String getMetaKey() {
        return this.metaKey;
    }

    public void setMetaKey(String str) {
        this.metaKey = str;
    }

    public String getMetaValue() {
        return this.metaValue;
    }

    public void setMetaValue(String str) {
        this.metaValue = str;
    }
}
