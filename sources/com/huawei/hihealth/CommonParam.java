package com.huawei.hihealth;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class CommonParam implements Parcelable {
    public static final Parcelable.Creator<CommonParam> CREATOR = new Parcelable.Creator<CommonParam>() { // from class: com.huawei.hihealth.CommonParam.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buh_, reason: merged with bridge method [inline-methods] */
        public CommonParam createFromParcel(Parcel parcel) {
            return new CommonParam(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public CommonParam[] newArray(int i) {
            return new CommonParam[i];
        }
    };
    protected Bundle valueHolder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CommonParam() {
        this.valueHolder = new Bundle();
    }

    public CommonParam(Parcel parcel) {
        this.valueHolder = parcel.readBundle();
    }

    public void putInt(String str, int i) {
        this.valueHolder.putInt(str, i);
    }

    public void putDouble(String str, double d) {
        this.valueHolder.putDouble(str, d);
    }

    public void putString(String str, String str2) {
        this.valueHolder.putString(str, str2);
    }

    public void putBoolean(String str, boolean z) {
        this.valueHolder.putBoolean(str, z);
    }

    public int getInt(String str) {
        return this.valueHolder.getInt(str);
    }

    public int getInt(String str, int i) {
        return this.valueHolder.getInt(str, i);
    }

    public double getDouble(String str) {
        return this.valueHolder.getDouble(str);
    }

    public String getString(String str) {
        return this.valueHolder.getString(str);
    }

    public boolean getBoolean(String str) {
        return this.valueHolder.getBoolean(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.valueHolder);
    }
}
