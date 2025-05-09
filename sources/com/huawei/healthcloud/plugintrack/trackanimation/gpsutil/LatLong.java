package com.huawei.healthcloud.plugintrack.trackanimation.gpsutil;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.hat;
import defpackage.hjd;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class LatLong implements Parcelable, Serializable {
    public static final Parcelable.Creator<LatLong> CREATOR = new Parcelable.Creator<LatLong>() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.LatLong.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public LatLong[] newArray(int i) {
            return new LatLong[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aYi_, reason: merged with bridge method [inline-methods] */
        public LatLong createFromParcel(Parcel parcel) {
            return new LatLong(parcel);
        }
    };
    public static final int DEFAULT_LINE_COLOR = -1;
    public static final int DOTTED_LINE = -1;
    public static final int SOLID_LINE = 1;
    private static final String TAG = "Track_LatLong";
    private static final long serialVersionUID = 6179237130649038426L;
    private double mAngle;
    private double mAreaFraction;
    private double mDistance;
    private int mIndex;
    private boolean mIsAreaEnd;
    private boolean mIsAreaStart;
    private boolean mIsInArea;
    private hjd mLatLng;
    private hat mLatLongArea;
    private int mLineColor;
    private int mLineStatus;
    private double mMultiplexField;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLong() {
        this.mLatLng = null;
        this.mMultiplexField = 0.0d;
        this.mLineColor = -1;
        this.mIndex = 0;
        this.mDistance = 0.0d;
        this.mAngle = 0.0d;
        this.mLineStatus = 1;
        this.mIsInArea = false;
        this.mIsAreaStart = false;
        this.mIsAreaEnd = false;
        this.mLatLongArea = new hat(0, 0, new hjd(0.0d, 0.0d), new hjd(0.0d, 0.0d));
        this.mAreaFraction = 0.0d;
        this.mLatLng = new hjd(0.0d, 0.0d);
    }

    public LatLong(double d, double d2) {
        this.mLatLng = null;
        this.mMultiplexField = 0.0d;
        this.mLineColor = -1;
        this.mIndex = 0;
        this.mDistance = 0.0d;
        this.mAngle = 0.0d;
        this.mLineStatus = 1;
        this.mIsInArea = false;
        this.mIsAreaStart = false;
        this.mIsAreaEnd = false;
        this.mLatLongArea = new hat(0, 0, new hjd(0.0d, 0.0d), new hjd(0.0d, 0.0d));
        this.mAreaFraction = 0.0d;
        this.mLatLng = new hjd(d, d2);
    }

    public LatLong(double d, double d2, double d3) {
        this(d, d2);
        this.mMultiplexField = d3;
    }

    public LatLong(hjd hjdVar) {
        this.mLatLng = null;
        this.mMultiplexField = 0.0d;
        this.mLineColor = -1;
        this.mIndex = 0;
        this.mDistance = 0.0d;
        this.mAngle = 0.0d;
        this.mLineStatus = 1;
        this.mIsInArea = false;
        this.mIsAreaStart = false;
        this.mIsAreaEnd = false;
        this.mLatLongArea = new hat(0, 0, new hjd(0.0d, 0.0d), new hjd(0.0d, 0.0d));
        this.mAreaFraction = 0.0d;
        this.mLatLng = hjdVar;
    }

    public LatLong(hjd hjdVar, double d) {
        this(hjdVar);
        this.mMultiplexField = d;
    }

    public LatLong(Parcel parcel) {
        this.mLatLng = null;
        this.mMultiplexField = 0.0d;
        this.mLineColor = -1;
        this.mIndex = 0;
        this.mDistance = 0.0d;
        this.mAngle = 0.0d;
        this.mLineStatus = 1;
        this.mIsInArea = false;
        this.mIsAreaStart = false;
        this.mIsAreaEnd = false;
        this.mLatLongArea = new hat(0, 0, new hjd(0.0d, 0.0d), new hjd(0.0d, 0.0d));
        this.mAreaFraction = 0.0d;
        this.mLatLng = new hjd(parcel.readDouble(), parcel.readDouble());
        this.mMultiplexField = parcel.readDouble();
        this.mDistance = parcel.readDouble();
        this.mAngle = parcel.readDouble();
        this.mLineStatus = parcel.readInt();
        this.mIndex = parcel.readInt();
        this.mLineColor = parcel.readInt();
        if (Build.VERSION.SDK_INT >= 29) {
            this.mIsInArea = parcel.readBoolean();
            this.mIsAreaStart = parcel.readBoolean();
            this.mIsAreaEnd = parcel.readBoolean();
        } else {
            this.mIsInArea = parcel.readInt() == 1;
            this.mIsAreaStart = parcel.readInt() == 1;
            this.mIsAreaEnd = parcel.readInt() == 1;
        }
        this.mLatLongArea = new hat(parcel.readInt(), parcel.readInt(), new hjd(parcel.readDouble(), parcel.readDouble()), new hjd(parcel.readDouble(), parcel.readDouble()));
        this.mAreaFraction = parcel.readDouble();
    }

    public hjd getLatLng() {
        return this.mLatLng;
    }

    public LatLong setLineStatus(int i) {
        this.mLineStatus = i;
        return this;
    }

    public LatLong setAngle(double d) {
        this.mAngle = d;
        return this;
    }

    public LatLong setDistance(double d) {
        this.mDistance = d;
        return this;
    }

    public LatLong setMultiplexField(double d) {
        this.mMultiplexField = d;
        return this;
    }

    public LatLong setIsInArea(boolean z) {
        this.mIsInArea = z;
        return this;
    }

    public LatLong setAreaStart(boolean z) {
        this.mIsAreaStart = z;
        return this;
    }

    public LatLong setAreaEnd(boolean z) {
        this.mIsAreaEnd = z;
        return this;
    }

    public LatLong setLatLongArea(hat hatVar) {
        this.mLatLongArea = hatVar;
        return this;
    }

    public LatLong setAreaFraction(double d) {
        this.mAreaFraction = d;
        return this;
    }

    public double getMultiplexField() {
        return this.mMultiplexField;
    }

    public LatLong setIndex(int i) {
        this.mIndex = i;
        return this;
    }

    public int getLineStatus() {
        return this.mLineStatus;
    }

    public double getDistance() {
        return this.mDistance;
    }

    public double getAngle() {
        return this.mAngle;
    }

    public boolean getIsInArea() {
        return this.mIsInArea;
    }

    public double getAreaFraction() {
        return this.mAreaFraction;
    }

    public int getIndex() {
        return this.mIndex;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mLatLng.b);
        parcel.writeDouble(this.mLatLng.d);
        parcel.writeDouble(this.mMultiplexField);
        parcel.writeDouble(this.mDistance);
        parcel.writeDouble(this.mAngle);
        parcel.writeInt(this.mLineStatus);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mLineColor);
        if (Build.VERSION.SDK_INT >= 29) {
            parcel.writeBoolean(this.mIsInArea);
            parcel.writeBoolean(this.mIsAreaStart);
            parcel.writeBoolean(this.mIsAreaEnd);
        } else {
            parcel.writeInt(this.mIsInArea ? 1 : 0);
            parcel.writeInt(this.mIsAreaStart ? 1 : 0);
            parcel.writeInt(this.mIsAreaEnd ? 1 : 0);
        }
        parcel.writeInt(this.mLatLongArea.b());
        parcel.writeInt(this.mLatLongArea.d());
        parcel.writeDouble(this.mLatLongArea.a().b);
        parcel.writeDouble(this.mLatLongArea.a().d);
        parcel.writeDouble(this.mLatLongArea.c().b);
        parcel.writeDouble(this.mLatLongArea.c().d);
        parcel.writeDouble(this.mAreaFraction);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeDouble(this.mLatLng.b);
        objectOutputStream.writeDouble(this.mLatLng.d);
        objectOutputStream.writeDouble(this.mMultiplexField);
        objectOutputStream.writeDouble(this.mDistance);
        objectOutputStream.writeDouble(this.mAngle);
        objectOutputStream.writeInt(this.mLineStatus);
        objectOutputStream.writeInt(this.mIndex);
        objectOutputStream.writeInt(this.mLineColor);
        if (Build.VERSION.SDK_INT >= 29) {
            objectOutputStream.writeBoolean(this.mIsInArea);
            objectOutputStream.writeBoolean(this.mIsAreaStart);
            objectOutputStream.writeBoolean(this.mIsAreaEnd);
        } else {
            objectOutputStream.writeInt(this.mIsInArea ? 1 : 0);
            objectOutputStream.writeInt(this.mIsAreaStart ? 1 : 0);
            objectOutputStream.writeInt(this.mIsAreaEnd ? 1 : 0);
        }
        objectOutputStream.writeInt(this.mLatLongArea.b());
        objectOutputStream.writeInt(this.mLatLongArea.d());
        objectOutputStream.writeDouble(this.mLatLongArea.a().b);
        objectOutputStream.writeDouble(this.mLatLongArea.a().d);
        objectOutputStream.writeDouble(this.mLatLongArea.c().b);
        objectOutputStream.writeDouble(this.mLatLongArea.c().d);
        objectOutputStream.writeDouble(this.mAreaFraction);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.mLatLng = new hjd(objectInputStream.readDouble(), objectInputStream.readDouble());
        this.mMultiplexField = objectInputStream.readDouble();
        this.mDistance = objectInputStream.readDouble();
        this.mAngle = objectInputStream.readDouble();
        this.mLineStatus = objectInputStream.readInt();
        this.mIndex = objectInputStream.readInt();
        this.mLineColor = objectInputStream.readInt();
        if (Build.VERSION.SDK_INT >= 29) {
            this.mIsInArea = objectInputStream.readBoolean();
            this.mIsAreaStart = objectInputStream.readBoolean();
            this.mIsAreaEnd = objectInputStream.readBoolean();
        } else {
            this.mIsInArea = objectInputStream.readInt() == 1;
            this.mIsAreaStart = objectInputStream.readInt() == 1;
            this.mIsAreaEnd = objectInputStream.readInt() == 1;
        }
        this.mLatLongArea = new hat(objectInputStream.readInt(), objectInputStream.readInt(), new hjd(objectInputStream.readDouble(), objectInputStream.readDouble()), new hjd(objectInputStream.readDouble(), objectInputStream.readDouble()));
        this.mAreaFraction = objectInputStream.readDouble();
    }
}
