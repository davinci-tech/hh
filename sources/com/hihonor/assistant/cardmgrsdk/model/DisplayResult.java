package com.hihonor.assistant.cardmgrsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class DisplayResult implements Parcelable {
    public static final Parcelable.Creator<DisplayResult> CREATOR = new Parcelable.Creator<DisplayResult>() { // from class: com.hihonor.assistant.cardmgrsdk.model.DisplayResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayResult[] newArray(int i) {
            return new DisplayResult[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DisplayResult createFromParcel(Parcel parcel) {
            return new DisplayResult(parcel);
        }
    };
    public String business;
    public String businessId;
    public int resultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.business);
        parcel.writeString(this.businessId);
        parcel.writeInt(this.resultCode);
    }

    public String toString() {
        return "DisplayResult{businessId='" + this.businessId + "', business='" + this.business + "', resultCode=" + this.resultCode + '}';
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public void setBusinessId(String str) {
        this.businessId = str;
    }

    public void setBusiness(String str) {
        this.business = str;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public String getBusinessId() {
        return this.businessId;
    }

    public String getBusiness() {
        return this.business;
    }

    public DisplayResult(String str, String str2, int i) {
        this.businessId = str2;
        this.business = str;
        this.resultCode = i;
    }

    public DisplayResult(Parcel parcel) {
        this.business = parcel.readString();
        this.businessId = parcel.readString();
        this.resultCode = parcel.readInt();
    }

    public DisplayResult() {
    }
}
