package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiUserPreferenceData implements Parcelable {
    public static final Parcelable.Creator<HiUserPreferenceData> CREATOR = new Parcelable.Creator<HiUserPreferenceData>() { // from class: com.huawei.hihealth.HiUserPreferenceData.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bvb_, reason: merged with bridge method [inline-methods] */
        public HiUserPreferenceData createFromParcel(Parcel parcel) {
            return new HiUserPreferenceData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HiUserPreferenceData[] newArray(int i) {
            return new HiUserPreferenceData[i];
        }
    };
    private String key;
    private String value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiUserPreferenceData() {
    }

    public HiUserPreferenceData(String str, String str2) {
        this.key = str;
        this.value = str2;
    }

    protected HiUserPreferenceData(Parcel parcel) {
        this.key = parcel.readString();
        this.value = parcel.readString();
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiUserPreference{key='");
        stringBuffer.append(this.key).append("', value='");
        stringBuffer.append(this.value).append("'}");
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeString(this.value);
    }
}
