package com.huawei.ui.main.stories.template;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class ComponentParam implements Parcelable {
    private static final int CONSTANT = 0;
    public static final Parcelable.Creator<ComponentParam> CREATOR = new Parcelable.Creator<ComponentParam>() { // from class: com.huawei.ui.main.stories.template.ComponentParam.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dTV_, reason: merged with bridge method [inline-methods] */
        public ComponentParam createFromParcel(Parcel parcel) {
            return new ComponentParam(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ComponentParam[] newArray(int i) {
            return new ComponentParam[i];
        }
    };

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("value")
    @Expose
    private String mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ComponentParam(Parcel parcel) {
        if (parcel != null) {
            this.mName = parcel.readString();
            this.mValue = parcel.readString();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mValue);
    }

    public void setmName(String str) {
        this.mName = str;
    }

    public String getmValue() {
        return this.mValue;
    }

    public void setmValue(String str) {
        this.mValue = str;
    }
}
