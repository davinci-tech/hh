package com.huawei.hihealth;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiDataUpdateOption implements Parcelable {
    public static final Parcelable.Creator<HiDataUpdateOption> CREATOR = new Parcelable.Creator<HiDataUpdateOption>() { // from class: com.huawei.hihealth.HiDataUpdateOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buC_, reason: merged with bridge method [inline-methods] */
        public HiDataUpdateOption createFromParcel(Parcel parcel) {
            return new HiDataUpdateOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HiDataUpdateOption[] newArray(int i) {
            return new HiDataUpdateOption[i];
        }
    };
    private int type;
    private ContentValues valueHolder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiDataUpdateOption() {
        this.type = 100;
        this.valueHolder = new ContentValues();
    }

    public HiDataUpdateOption(int i) {
        this.type = i;
        this.valueHolder = new ContentValues();
    }

    protected HiDataUpdateOption(Parcel parcel) {
        this.type = 100;
        this.type = parcel.readInt();
        this.valueHolder = (ContentValues) parcel.readParcelable(ContentValues.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeParcelable(this.valueHolder, i);
    }

    public final void putString(String str, String str2) {
        this.valueHolder.put(str, str2);
    }

    public String getString(String str) {
        return this.valueHolder.getAsString(str);
    }

    public void putBoolean(String str, boolean z) {
        this.valueHolder.put(str, Boolean.valueOf(z));
    }

    public boolean getBoolean(String str) {
        Boolean asBoolean = this.valueHolder.getAsBoolean(str);
        if (asBoolean == null) {
            return false;
        }
        return asBoolean.booleanValue();
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
