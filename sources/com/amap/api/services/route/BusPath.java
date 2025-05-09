package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class BusPath extends Path implements Parcelable {
    public static final Parcelable.Creator<BusPath> CREATOR = new Parcelable.Creator<BusPath>() { // from class: com.amap.api.services.route.BusPath.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ BusPath[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ BusPath createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static BusPath a(Parcel parcel) {
            return new BusPath(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private float f1523a;
    private boolean b;
    private float c;
    private float d;
    private List<BusStep> e;

    @Override // com.amap.api.services.route.Path, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float getCost() {
        return this.f1523a;
    }

    public void setCost(float f) {
        this.f1523a = f;
    }

    public boolean isNightBus() {
        return this.b;
    }

    public void setNightBus(boolean z) {
        this.b = z;
    }

    public float getWalkDistance() {
        return this.c;
    }

    public void setWalkDistance(float f) {
        this.c = f;
    }

    public float getBusDistance() {
        return this.d;
    }

    public void setBusDistance(float f) {
        this.d = f;
    }

    public List<BusStep> getSteps() {
        return this.e;
    }

    public void setSteps(List<BusStep> list) {
        this.e = list;
    }

    @Override // com.amap.api.services.route.Path, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.f1523a);
        parcel.writeBooleanArray(new boolean[]{this.b});
        parcel.writeFloat(this.c);
        parcel.writeFloat(this.d);
        parcel.writeTypedList(this.e);
    }

    public BusPath(Parcel parcel) {
        super(parcel);
        this.e = new ArrayList();
        this.f1523a = parcel.readFloat();
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.b = zArr[0];
        this.c = parcel.readFloat();
        this.d = parcel.readFloat();
        this.e = parcel.createTypedArrayList(BusStep.CREATOR);
    }

    public BusPath() {
        this.e = new ArrayList();
    }
}
