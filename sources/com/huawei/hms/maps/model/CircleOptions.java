package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import java.util.List;

/* loaded from: classes9.dex */
public class CircleOptions implements Parcelable {
    public static final Parcelable.Creator<CircleOptions> CREATOR = new Parcelable.Creator<CircleOptions>() { // from class: com.huawei.hms.maps.model.CircleOptions.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CircleOptions[] newArray(int i) {
            return new CircleOptions[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CircleOptions createFromParcel(Parcel parcel) {
            return new CircleOptions(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private LatLng f4992a;
    private int b;
    private double c;
    private int d;
    private List<PatternItem> e;
    private float f;
    private float g;
    private boolean h;
    private boolean i;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CircleOptions zIndex(float f) {
        this.g = f;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeParcelable(2, this.f4992a, i, false);
        parcelWrite.writeInt(3, this.b);
        parcelWrite.writeDouble(4, this.c);
        parcelWrite.writeInt(5, this.d);
        parcelWrite.writeTypedList(6, this.e, false);
        parcelWrite.writeFloat(7, this.f);
        parcelWrite.writeFloat(8, this.g);
        parcelWrite.writeBoolean(9, this.h);
        parcelWrite.writeBoolean(10, this.i);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public CircleOptions visible(boolean z) {
        this.i = z;
        return this;
    }

    public CircleOptions strokeWidth(float f) {
        this.f = f;
        return this;
    }

    public CircleOptions strokePattern(List<PatternItem> list) {
        this.e = list;
        return this;
    }

    public CircleOptions strokeColor(int i) {
        this.d = i;
        return this;
    }

    public CircleOptions radius(double d) {
        this.c = d;
        return this;
    }

    public boolean isVisible() {
        return this.i;
    }

    public boolean isClickable() {
        return this.h;
    }

    public float getZIndex() {
        return this.g;
    }

    public float getStrokeWidth() {
        return this.f;
    }

    public List<PatternItem> getStrokePattern() {
        return this.e;
    }

    public int getStrokeColor() {
        return this.d;
    }

    public double getRadius() {
        return this.c;
    }

    public int getFillColor() {
        return this.b;
    }

    public LatLng getCenter() {
        return this.f4992a;
    }

    public CircleOptions fillColor(int i) {
        this.b = i;
        return this;
    }

    public CircleOptions clickable(boolean z) {
        this.h = z;
        return this;
    }

    public CircleOptions center(LatLng latLng) {
        this.f4992a = latLng;
        return this;
    }

    protected CircleOptions(Parcel parcel) {
        this.b = 0;
        this.d = -16777216;
        this.f = 10.0f;
        this.g = 0.0f;
        this.h = false;
        this.i = true;
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.f4992a = (LatLng) parcelReader.readParcelable(2, LatLng.CREATOR, null);
        this.b = parcelReader.readInt(3, 0);
        this.c = parcelReader.readDouble(4, 0.0d);
        this.d = parcelReader.readInt(5, 0);
        this.e = parcelReader.createTypedList(6, PatternItem.CREATOR, null);
        this.f = parcelReader.readFloat(7, 0.0f);
        this.g = parcelReader.readFloat(8, 0.0f);
        this.h = parcelReader.readBoolean(9, true);
        this.i = parcelReader.readBoolean(10, true);
    }

    public CircleOptions() {
        this.b = 0;
        this.d = -16777216;
        this.f = 10.0f;
        this.g = 0.0f;
        this.h = false;
        this.i = true;
    }
}
