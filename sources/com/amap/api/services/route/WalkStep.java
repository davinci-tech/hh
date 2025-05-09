package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class WalkStep implements Parcelable {
    public static final Parcelable.Creator<WalkStep> CREATOR = new Parcelable.Creator<WalkStep>() { // from class: com.amap.api.services.route.WalkStep.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ WalkStep[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ WalkStep createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static WalkStep a(Parcel parcel) {
            return new WalkStep(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f1587a;
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

    public String getInstruction() {
        return this.f1587a;
    }

    public void setInstruction(String str) {
        this.f1587a = str;
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

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1587a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.e);
        parcel.writeTypedList(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
    }

    public WalkStep(Parcel parcel) {
        this.f = new ArrayList();
        this.f1587a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readFloat();
        this.e = parcel.readFloat();
        this.f = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.g = parcel.readString();
        this.h = parcel.readString();
    }

    public WalkStep() {
        this.f = new ArrayList();
    }
}
