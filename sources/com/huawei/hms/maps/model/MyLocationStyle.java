package com.huawei.hms.maps.model;

import android.graphics.Color;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.feature.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public class MyLocationStyle implements Parcelable {
    public static final Parcelable.Creator<MyLocationStyle> CREATOR = new Parcelable.Creator<MyLocationStyle>() { // from class: com.huawei.hms.maps.model.MyLocationStyle.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MyLocationStyle[] newArray(int i) {
            return new MyLocationStyle[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MyLocationStyle createFromParcel(Parcel parcel) {
            return new MyLocationStyle(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private float f5005a;
    private float b;
    private BitmapDescriptor c;
    private int d;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeFloat(2, this.f5005a);
        parcelWrite.writeFloat(3, this.b);
        BitmapDescriptor bitmapDescriptor = this.c;
        parcelWrite.writeIBinder(4, bitmapDescriptor != null ? bitmapDescriptor.getObject().asBinder() : null, true);
        parcelWrite.writeInt(5, this.d);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public MyLocationStyle radiusFillColor(int i) {
        this.d = i;
        return this;
    }

    public MyLocationStyle myLocationIcon(BitmapDescriptor bitmapDescriptor) {
        this.c = bitmapDescriptor;
        return this;
    }

    public int getRadiusFillColor() {
        return this.d;
    }

    public BitmapDescriptor getMyLocationIcon() {
        return this.c;
    }

    public float getAnchorV() {
        return this.b;
    }

    public float getAnchorU() {
        return this.f5005a;
    }

    public MyLocationStyle anchor(float f, float f2) {
        this.f5005a = f;
        this.b = f2;
        return this;
    }

    protected MyLocationStyle(Parcel parcel) {
        this.f5005a = 0.5f;
        this.b = 0.5f;
        this.d = Color.argb(56, 117, 112, 255);
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.f5005a = parcelReader.readFloat(2, 0.0f);
        this.b = parcelReader.readFloat(3, 0.0f);
        IBinder readIBinder = parcelReader.readIBinder(4, null);
        if (readIBinder != null) {
            this.c = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(readIBinder));
        }
        this.d = parcelReader.readInt(5, 0);
    }

    public MyLocationStyle() {
        this.f5005a = 0.5f;
        this.b = 0.5f;
        this.d = Color.argb(56, 117, 112, 255);
    }
}
