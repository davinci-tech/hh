package com.huawei.controlcenter.featureability.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class ExtraParams implements Parcelable {
    public static final Parcelable.Creator<ExtraParams> CREATOR = new Parcelable.Creator<ExtraParams>() { // from class: com.huawei.controlcenter.featureability.sdk.model.ExtraParams.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: pN_, reason: merged with bridge method [inline-methods] */
        public ExtraParams createFromParcel(Parcel parcel) {
            ExtraParams extraParams = new ExtraParams();
            if (parcel == null) {
                return extraParams;
            }
            extraParams.setDevType(parcel.createStringArray());
            extraParams.setTargetPkgName(parcel.readString());
            extraParams.setDescription(parcel.readString());
            extraParams.setJsonParams(parcel.readString());
            return extraParams;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ExtraParams[] newArray(int i) {
            return new ExtraParams[i];
        }
    };
    private String mDescription;
    private String[] mDevType;
    private String mJsonParams;
    private String mTargetPkgName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(this.mDevType);
        parcel.writeString(this.mTargetPkgName);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mJsonParams);
    }

    public String[] getDevType() {
        return this.mDevType;
    }

    public void setDevType(String[] strArr) {
        this.mDevType = strArr;
    }

    public String getTargetPkgName() {
        return this.mTargetPkgName;
    }

    public void setTargetPkgName(String str) {
        this.mTargetPkgName = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public String getJsonParams() {
        return this.mJsonParams;
    }

    public void setJsonParams(String str) {
        this.mJsonParams = str;
    }
}
