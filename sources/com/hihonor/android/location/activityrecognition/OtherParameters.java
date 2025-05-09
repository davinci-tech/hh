package com.hihonor.android.location.activityrecognition;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class OtherParameters implements Parcelable {
    public static final Parcelable.Creator<OtherParameters> CREATOR = new Parcelable.Creator<OtherParameters>() { // from class: com.hihonor.android.location.activityrecognition.OtherParameters.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: cR_, reason: merged with bridge method [inline-methods] */
        public OtherParameters createFromParcel(Parcel parcel) {
            return new OtherParameters(parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public OtherParameters[] newArray(int i) {
            return new OtherParameters[i];
        }
    };
    private double mParam1;
    private double mParam2;
    private double mParam3;
    private double mParam4;
    private String mParam5;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OtherParameters(double d, double d2, double d3, double d4, String str) {
        this.mParam1 = d;
        this.mParam2 = d2;
        this.mParam3 = d3;
        this.mParam4 = d4;
        this.mParam5 = str;
    }

    public void setmParam1(double d) {
        this.mParam1 = d;
    }

    public void setmParam2(double d) {
        this.mParam2 = d;
    }

    public void setmParam3(double d) {
        this.mParam3 = d;
    }

    public void setmParam4(double d) {
        this.mParam4 = d;
    }

    public void setmParam5(String str) {
        this.mParam5 = str;
    }

    public double getmParam1() {
        return this.mParam1;
    }

    public double getmParam2() {
        return this.mParam2;
    }

    public double getmParam3() {
        return this.mParam3;
    }

    public double getmParam4() {
        return this.mParam4;
    }

    public String getmParam5() {
        return this.mParam5;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mParam1);
        parcel.writeDouble(this.mParam2);
        parcel.writeDouble(this.mParam3);
        parcel.writeDouble(this.mParam4);
        parcel.writeString(this.mParam5);
    }

    public String toString() {
        return String.format("Param1=%s, Param2=%s, Param3=%s, Param4=%s, Param5=%s", Double.valueOf(this.mParam1), Double.valueOf(this.mParam2), Double.valueOf(this.mParam3), Double.valueOf(this.mParam4), this.mParam5);
    }
}
