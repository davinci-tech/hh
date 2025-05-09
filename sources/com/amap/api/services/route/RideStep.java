package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class RideStep implements Parcelable {
    public static final Parcelable.Creator<RideStep> CREATOR = new Parcelable.Creator<RideStep>() { // from class: com.amap.api.services.route.RideStep.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ RideStep createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ RideStep[] newArray(int i) {
            return a(i);
        }

        private static RideStep a(Parcel parcel) {
            return new RideStep(parcel);
        }

        private static RideStep[] a(int i) {
            return new RideStep[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1551a;
    private String b;
    private String c;
    private float d;
    private float e;
    private List<LatLonPoint> f;
    private String g;
    private String h;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RideStep() {
        this.f = new ArrayList();
    }

    public String getInstruction() {
        return this.f1551a;
    }

    public void setInstruction(String str) {
        this.f1551a = str;
    }

    public String getOrientation() {
        return this.b;
    }

    public void setOrientation(String str) {
        this.b = str;
    }

    public String getRoad() {
        return this.c;
    }

    public void setRoad(String str) {
        this.c = str;
    }

    public float getDistance() {
        return this.d;
    }

    public void setDistance(float f) {
        this.d = f;
    }

    public float getDuration() {
        return this.e;
    }

    public void setDuration(float f) {
        this.e = f;
    }

    public List<LatLonPoint> getPolyline() {
        return this.f;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.f = list;
    }

    public String getAction() {
        return this.g;
    }

    public void setAction(String str) {
        this.g = str;
    }

    public String getAssistantAction() {
        return this.h;
    }

    public void setAssistantAction(String str) {
        this.h = str;
    }

    protected RideStep(Parcel parcel) {
        this.f = new ArrayList();
        this.f1551a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readFloat();
        this.e = parcel.readFloat();
        this.f = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.g = parcel.readString();
        this.h = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1551a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
        parcel.writeTypedList(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
    }
}
