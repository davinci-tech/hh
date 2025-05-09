package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class HeatMapOptions implements Parcelable {
    public static final Parcelable.Creator<HeatMapOptions> CREATOR = new Parcelable.Creator<HeatMapOptions>() { // from class: com.huawei.hms.maps.model.HeatMapOptions.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HeatMapOptions[] newArray(int i) {
            return new HeatMapOptions[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HeatMapOptions createFromParcel(Parcel parcel) {
            return new HeatMapOptions(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private String f4996a;
    private boolean b;
    private Map<Float, Integer> c;
    private Map<Float, Float> d;
    private Map<Float, Float> e;
    private Map<Float, Float> f;
    private int g;
    private int h;

    public enum RadiusUnit {
        PIXEL,
        METER
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4996a);
        parcel.writeByte(this.b ? (byte) 1 : (byte) 0);
        parcel.writeMap(this.c);
        parcel.writeMap(this.d);
        parcel.writeMap(this.e);
        parcel.writeMap(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
    }

    public void visible(boolean z) {
        this.b = z;
    }

    public void setResourceId(int i) {
        this.h = i;
    }

    public void radiusUnit(RadiusUnit radiusUnit) {
        this.g = radiusUnit.equals(RadiusUnit.METER) ? 2 : 1;
    }

    public void radius(Map<Float, Float> map) {
        if (map == null) {
            return;
        }
        this.f.clear();
        this.f.putAll(map);
    }

    public void radius(float f) {
        this.f.clear();
        this.f.put(Float.valueOf(0.0f), Float.valueOf(f));
    }

    public void opacity(Map<Float, Float> map) {
        if (map == null) {
            return;
        }
        this.d.clear();
        this.d.putAll(map);
    }

    public void opacity(float f) {
        this.d.clear();
        this.d.put(Float.valueOf(-1.0f), Float.valueOf(f));
    }

    public void intensity(Map<Float, Float> map) {
        if (map == null) {
            return;
        }
        this.e.clear();
        this.e.putAll(map);
    }

    public void intensity(float f) {
        this.e.clear();
        this.e.put(Float.valueOf(0.0f), Float.valueOf(f));
    }

    public boolean getVisible() {
        return this.b;
    }

    public int getResourceId() {
        return this.h;
    }

    public RadiusUnit getRadiusUnit() {
        return this.g == 2 ? RadiusUnit.METER : RadiusUnit.PIXEL;
    }

    public Map<Float, Float> getRadius() {
        return this.f;
    }

    public Map<Float, Float> getOpacity() {
        return this.d;
    }

    public Map<Float, Float> getIntensity() {
        return this.e;
    }

    public String getHeatMapData() {
        return this.f4996a;
    }

    public Map<Float, Integer> getColor() {
        return this.c;
    }

    public void dataSet(String str) {
        this.f4996a = str;
    }

    public void dataSet(int i) {
        this.h = i;
    }

    public void color(Map<Float, Integer> map) {
        if (map == null) {
            return;
        }
        this.c.clear();
        this.c.putAll(map);
    }

    protected HeatMapOptions(Parcel parcel) {
        this.b = true;
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = 1;
        this.f4996a = parcel.readString();
        this.b = parcel.readByte() != 0;
        HashMap hashMap = new HashMap();
        this.c = hashMap;
        parcel.readMap(hashMap, getClass().getClassLoader());
        HashMap hashMap2 = new HashMap();
        this.d = hashMap2;
        parcel.readMap(hashMap2, getClass().getClassLoader());
        HashMap hashMap3 = new HashMap();
        this.e = hashMap3;
        parcel.readMap(hashMap3, getClass().getClassLoader());
        HashMap hashMap4 = new HashMap();
        this.f = hashMap4;
        parcel.readMap(hashMap4, getClass().getClassLoader());
        this.g = parcel.readInt();
        this.h = parcel.readInt();
    }

    public HeatMapOptions() {
        this.b = true;
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = new HashMap();
        this.g = 1;
    }
}
