package com.huawei.hms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.feature.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public class GroundOverlayOptions implements Parcelable {
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new Parcelable.Creator<GroundOverlayOptions>() { // from class: com.huawei.hms.maps.model.GroundOverlayOptions.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GroundOverlayOptions[] newArray(int i) {
            return new GroundOverlayOptions[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GroundOverlayOptions createFromParcel(Parcel parcel) {
            return new GroundOverlayOptions(parcel);
        }
    };
    public static final float NO_DIMENSION = -1.0f;

    /* renamed from: a, reason: collision with root package name */
    private float f4994a;
    private float b;
    private float c;
    private LatLngBounds d;
    private float e;
    private BitmapDescriptor f;
    private LatLng g;
    private float h;
    private float i;
    private float j;
    private boolean k;
    private boolean l;

    private boolean a(float f) {
        return f < 0.0f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GroundOverlayOptions zIndex(float f) {
        this.j = f;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeFloat(2, this.f4994a);
        parcelWrite.writeFloat(3, this.b);
        parcelWrite.writeFloat(4, this.c);
        parcelWrite.writeParcelable(5, this.d, i, false);
        parcelWrite.writeFloat(6, this.e);
        parcelWrite.writeIBinder(7, this.f.getObject().asBinder(), false);
        parcelWrite.writeParcelable(8, this.g, i, false);
        parcelWrite.writeFloat(9, this.h);
        parcelWrite.writeFloat(10, this.i);
        parcelWrite.writeFloat(11, this.j);
        parcelWrite.writeBoolean(12, this.k);
        parcelWrite.writeBoolean(13, this.l);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public GroundOverlayOptions visible(boolean z) {
        this.l = z;
        return this;
    }

    public GroundOverlayOptions transparency(float f) {
        if (f > 1.0f) {
            throw new IllegalArgumentException("The transparency value  is illegal,this value must be less than 1");
        }
        if (f < 0.0f) {
            throw new IllegalArgumentException("The transparency value  is illegal,this value must be greater than 0");
        }
        this.h = f;
        return this;
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        if (this.g != null) {
            throw new IllegalStateException("Set position from LatLngBounds failed,position has been set by position function,this value can not reassign.");
        }
        this.d = latLngBounds;
        return this;
    }

    public GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        if (this.g != null || this.d != null) {
            throw new IllegalStateException("GroundOverlay location is illegal,position has been set by positionFromBounds function,this value can not reassign.");
        }
        if (a(f)) {
            throw new IllegalArgumentException("GroundOverlay width value is illegal ,this value must be non-negative");
        }
        if (a(f2)) {
            throw new IllegalArgumentException("GroundOverlay height value is illegal,this value must be non-negative");
        }
        if (latLng == null) {
            throw new IllegalArgumentException("Location must be specified");
        }
        this.g = latLng;
        this.i = f;
        this.e = f2;
        return this;
    }

    public GroundOverlayOptions position(LatLng latLng, float f) {
        if (this.g != null || this.d != null) {
            throw new IllegalStateException("GroundOverlay location is illegal,position has been set positionFromBounds function,this value can not reassign.");
        }
        if (latLng == null) {
            throw new IllegalArgumentException("Location must be specified");
        }
        if (a(f)) {
            throw new IllegalArgumentException("GroundOverlay width value is illegal ,this value must be non-negative");
        }
        this.g = latLng;
        this.i = f;
        this.e = -1.0f;
        return this;
    }

    public boolean isVisible() {
        return this.l;
    }

    public boolean isSetPosition() {
        return (this.d == null && this.g == null) ? false : true;
    }

    public boolean isClickable() {
        return this.k;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new NullPointerException("image descriptor can not be null.");
        }
        this.f = bitmapDescriptor;
        return this;
    }

    public float getZIndex() {
        return this.j;
    }

    public float getWidth() {
        return this.i;
    }

    public float getTransparency() {
        return this.h;
    }

    public LatLng getLocation() {
        return this.g;
    }

    public BitmapDescriptor getImage() {
        return this.f;
    }

    public float getHeight() {
        return this.e;
    }

    public LatLngBounds getBounds() {
        return this.d;
    }

    public float getBearing() {
        return this.c;
    }

    public float getAnchorV() {
        return this.b;
    }

    public float getAnchorU() {
        return this.f4994a;
    }

    public GroundOverlayOptions clickable(boolean z) {
        this.k = z;
        return this;
    }

    public GroundOverlayOptions bearing(float f) {
        this.c = f;
        return this;
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        this.f4994a = f;
        this.b = f2;
        return this;
    }

    protected GroundOverlayOptions(Parcel parcel) {
        this.f4994a = 0.5f;
        this.b = 0.5f;
        this.h = 0.0f;
        this.l = true;
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.f4994a = parcelReader.readFloat(2, 0.0f);
        this.b = parcelReader.readFloat(3, 0.0f);
        this.c = parcelReader.readFloat(4, 0.0f);
        this.d = (LatLngBounds) parcelReader.readParcelable(5, LatLngBounds.CREATOR, null);
        this.e = parcelReader.readFloat(6, 0.0f);
        IBinder readIBinder = parcelReader.readIBinder(7, null);
        if (readIBinder != null) {
            this.f = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(readIBinder));
        }
        this.g = (LatLng) parcelReader.readParcelable(8, LatLng.CREATOR, null);
        this.h = parcelReader.readFloat(9, 0.0f);
        this.i = parcelReader.readFloat(10, 0.0f);
        this.j = parcelReader.readFloat(11, 0.0f);
        this.k = parcelReader.readBoolean(12, true);
        this.l = parcelReader.readBoolean(13, true);
    }

    public GroundOverlayOptions() {
        this.f4994a = 0.5f;
        this.b = 0.5f;
        this.h = 0.0f;
        this.l = true;
    }
}
