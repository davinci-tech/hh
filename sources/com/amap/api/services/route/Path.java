package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class Path implements Parcelable {
    public static final Parcelable.Creator<Path> CREATOR = new Parcelable.Creator<Path>() { // from class: com.amap.api.services.route.Path.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ Path[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Path createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        private static Path a(Parcel parcel) {
            return new Path(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private float f1545a;
    private long b;
    private List<LatLonPoint> c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<LatLonPoint> getPolyline() {
        return this.c;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.c = list;
    }

    public float getDistance() {
        return this.f1545a;
    }

    public void setDistance(float f) {
        this.f1545a = f;
    }

    public long getDuration() {
        return this.b;
    }

    public void setDuration(long j) {
        this.b = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f1545a);
        parcel.writeLong(this.b);
        parcel.writeTypedList(this.c);
    }

    public Path(Parcel parcel) {
        this.c = new ArrayList();
        this.f1545a = parcel.readFloat();
        this.b = parcel.readLong();
        this.c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
    }

    public Path() {
        this.c = new ArrayList();
    }
}
