package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class DistanceItem implements Parcelable {
    public static final Parcelable.Creator<DistanceItem> CREATOR = new Parcelable.Creator<DistanceItem>() { // from class: com.amap.api.services.route.DistanceItem.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DistanceItem createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DistanceItem[] newArray(int i) {
            return a(i);
        }

        private static DistanceItem a(Parcel parcel) {
            return new DistanceItem(parcel);
        }

        private static DistanceItem[] a(int i) {
            return new DistanceItem[i];
        }
    };
    public final int ERROR_CODE_NOT_IN_CHINA;
    public final int ERROR_CODE_NO_DRIVE;
    public final int ERROR_CODE_TOO_FAR;

    /* renamed from: a, reason: collision with root package name */
    private int f1528a;
    private int b;
    private float c;
    private float d;
    private String e;
    private int f;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getOriginId() {
        return this.f1528a;
    }

    public int getDestId() {
        return this.b;
    }

    public float getDistance() {
        return this.c;
    }

    public float getDuration() {
        return this.d;
    }

    public String getErrorInfo() {
        return this.e;
    }

    public int getErrorCode() {
        return this.f;
    }

    public void setOriginId(int i) {
        this.f1528a = i;
    }

    public void setDestId(int i) {
        this.b = i;
    }

    public void setDistance(float f) {
        this.c = f;
    }

    public void setDuration(float f) {
        this.d = f;
    }

    public void setErrorInfo(String str) {
        this.e = str;
    }

    public void setErrorCode(int i) {
        this.f = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f1528a);
        parcel.writeInt(this.b);
        parcel.writeFloat(this.c);
        parcel.writeFloat(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
    }

    public DistanceItem() {
        this.ERROR_CODE_NO_DRIVE = 1;
        this.ERROR_CODE_TOO_FAR = 2;
        this.ERROR_CODE_NOT_IN_CHINA = 3;
        this.f1528a = 1;
        this.b = 1;
        this.c = 0.0f;
        this.d = 0.0f;
    }

    protected DistanceItem(Parcel parcel) {
        this.ERROR_CODE_NO_DRIVE = 1;
        this.ERROR_CODE_TOO_FAR = 2;
        this.ERROR_CODE_NOT_IN_CHINA = 3;
        this.f1528a = 1;
        this.b = 1;
        this.c = 0.0f;
        this.d = 0.0f;
        this.f1528a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readFloat();
        this.d = parcel.readFloat();
        this.e = parcel.readString();
        this.f = parcel.readInt();
    }
}
