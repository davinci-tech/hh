package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes2.dex */
public class TruckPath implements Parcelable {
    public static final Parcelable.Creator<TruckPath> CREATOR = new Parcelable.Creator<TruckPath>() { // from class: com.amap.api.services.route.TruckPath.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ TruckPath createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ TruckPath[] newArray(int i) {
            return a(i);
        }

        private static TruckPath a(Parcel parcel) {
            return new TruckPath(parcel);
        }

        private static TruckPath[] a(int i) {
            return new TruckPath[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private float f1582a;
    private long b;
    private String c;
    private float d;
    private float e;
    private int f;
    private int g;
    private List<TruckStep> h;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TruckPath() {
    }

    protected TruckPath(Parcel parcel) {
        this.f1582a = parcel.readFloat();
        this.b = parcel.readLong();
        this.c = parcel.readString();
        this.d = parcel.readFloat();
        this.e = parcel.readFloat();
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.createTypedArrayList(TruckStep.CREATOR);
    }

    public void setDistance(float f) {
        this.f1582a = f;
    }

    public void setDuration(long j) {
        this.b = j;
    }

    public void setStrategy(String str) {
        this.c = str;
    }

    public void setTolls(float f) {
        this.d = f;
    }

    public void setTollDistance(float f) {
        this.e = f;
    }

    public void setTotalTrafficlights(int i) {
        this.f = i;
    }

    public void setRestriction(int i) {
        this.g = i;
    }

    public void setSteps(List<TruckStep> list) {
        this.h = list;
    }

    public float getDistance() {
        return this.f1582a;
    }

    public long getDuration() {
        return this.b;
    }

    public String getStrategy() {
        return this.c;
    }

    public float getTolls() {
        return this.d;
    }

    public float getTollDistance() {
        return this.e;
    }

    public int getTotalTrafficlights() {
        return this.f;
    }

    public int getRestriction() {
        return this.g;
    }

    public List<TruckStep> getSteps() {
        return this.h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f1582a);
        parcel.writeLong(this.b);
        parcel.writeString(this.c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeTypedList(this.h);
    }
}
