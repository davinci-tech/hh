package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.List;

/* loaded from: classes2.dex */
public class TruckStep implements Parcelable {
    public static final Parcelable.Creator<TruckStep> CREATOR = new Parcelable.Creator<TruckStep>() { // from class: com.amap.api.services.route.TruckStep.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ TruckStep createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ TruckStep[] newArray(int i) {
            return a(i);
        }

        private static TruckStep a(Parcel parcel) {
            return new TruckStep(parcel);
        }

        private static TruckStep[] a(int i) {
            return new TruckStep[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1584a;
    private String b;
    private String c;
    private float d;
    private float e;
    private float f;
    private String g;
    private float h;
    private List<LatLonPoint> i;
    private String j;
    private String k;
    private List<RouteSearchCity> l;
    private List<TMC> m;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TruckStep() {
    }

    protected TruckStep(Parcel parcel) {
        this.f1584a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readFloat();
        this.e = parcel.readFloat();
        this.f = parcel.readFloat();
        this.g = parcel.readString();
        this.h = parcel.readFloat();
        this.i = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.createTypedArrayList(RouteSearchCity.CREATOR);
        this.m = parcel.createTypedArrayList(TMC.CREATOR);
    }

    public void setInstruction(String str) {
        this.f1584a = str;
    }

    public void setOrientation(String str) {
        this.b = str;
    }

    public void setRoad(String str) {
        this.c = str;
    }

    public void setTolls(float f) {
        this.d = f;
    }

    public void setDistance(float f) {
        this.e = f;
    }

    public void setTollDistance(float f) {
        this.f = f;
    }

    public void setTollRoad(String str) {
        this.g = str;
    }

    public void setDuration(float f) {
        this.h = f;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.i = list;
    }

    public void setAction(String str) {
        this.j = str;
    }

    public void setAssistantAction(String str) {
        this.k = str;
    }

    public void setRouteSearchCityList(List<RouteSearchCity> list) {
        this.l = list;
    }

    public void setTMCs(List<TMC> list) {
        this.m = list;
    }

    public String getInstruction() {
        return this.f1584a;
    }

    public String getOrientation() {
        return this.b;
    }

    public String getRoad() {
        return this.c;
    }

    public float getTolls() {
        return this.d;
    }

    public float getDistance() {
        return this.e;
    }

    public float getTollDistance() {
        return this.f;
    }

    public String getTollRoad() {
        return this.g;
    }

    public float getDuration() {
        return this.h;
    }

    public List<LatLonPoint> getPolyline() {
        return this.i;
    }

    public String getAction() {
        return this.j;
    }

    public String getAssistantAction() {
        return this.k;
    }

    public List<RouteSearchCity> getRouteSearchCityList() {
        return this.l;
    }

    public List<TMC> getTMCs() {
        return this.m;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1584a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
        parcel.writeFloat(this.f);
        parcel.writeString(this.g);
        parcel.writeFloat(this.h);
        parcel.writeTypedList(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeTypedList(this.l);
        parcel.writeTypedList(this.m);
    }
}
