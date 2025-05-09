package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class DrivePlanPath implements Parcelable {
    public static final Parcelable.Creator<DrivePlanPath> CREATOR = new Parcelable.Creator<DrivePlanPath>() { // from class: com.amap.api.services.route.DrivePlanPath.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ DrivePlanPath[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DrivePlanPath createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static DrivePlanPath a(Parcel parcel) {
            return new DrivePlanPath(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    float f1536a;
    int b;
    private List<DrivePlanStep> c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float getDistance() {
        return this.f1536a;
    }

    public void setDistance(float f) {
        this.f1536a = f;
    }

    public float getTrafficLights() {
        return this.b;
    }

    public void setTrafficLights(int i) {
        this.b = i;
    }

    public List<DrivePlanStep> getSteps() {
        return this.c;
    }

    public void setSteps(List<DrivePlanStep> list) {
        this.c = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f1536a);
        parcel.writeInt(this.b);
        parcel.writeTypedList(this.c);
    }

    public DrivePlanPath(Parcel parcel) {
        this.c = new ArrayList();
        this.f1536a = parcel.readFloat();
        this.b = parcel.readInt();
        this.c = parcel.createTypedArrayList(DrivePlanStep.CREATOR);
    }

    public DrivePlanPath() {
        this.c = new ArrayList();
    }
}
