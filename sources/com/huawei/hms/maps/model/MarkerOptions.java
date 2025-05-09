package com.huawei.hms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.feature.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public class MarkerOptions implements Parcelable {
    public static final Parcelable.Creator<MarkerOptions> CREATOR = new Parcelable.Creator<MarkerOptions>() { // from class: com.huawei.hms.maps.model.MarkerOptions.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MarkerOptions[] newArray(int i) {
            return new MarkerOptions[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MarkerOptions createFromParcel(Parcel parcel) {
            return new MarkerOptions(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private LatLng f5004a;
    private String b;
    private String c;
    private BitmapDescriptor d;
    private float e;
    private float f;
    private boolean g;
    private boolean h;
    private boolean i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private boolean o;
    private float p;
    private float q;
    private boolean r;
    private boolean s;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MarkerOptions zIndex(float f) {
        this.n = f;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeParcelable(2, this.f5004a, i, false);
        parcelWrite.writeString(3, this.b, false);
        parcelWrite.writeString(4, this.c, false);
        parcelWrite.writeFloat(5, this.e);
        parcelWrite.writeFloat(6, this.f);
        parcelWrite.writeBoolean(7, this.g);
        parcelWrite.writeBoolean(8, this.h);
        parcelWrite.writeBoolean(9, this.i);
        parcelWrite.writeFloat(10, this.j);
        parcelWrite.writeFloat(11, this.k);
        parcelWrite.writeFloat(12, this.l);
        parcelWrite.writeFloat(13, this.m);
        parcelWrite.writeFloat(14, this.n);
        parcelWrite.writeBoolean(15, this.o);
        BitmapDescriptor bitmapDescriptor = this.d;
        parcelWrite.writeIBinder(16, bitmapDescriptor != null ? bitmapDescriptor.getObject().asBinder() : null, true);
        parcelWrite.writeFloat(17, this.p);
        parcelWrite.writeFloat(18, this.q);
        parcelWrite.writeBoolean(19, this.r);
        parcelWrite.writeBoolean(20, this.s);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public MarkerOptions visible(boolean z) {
        this.h = z;
        return this;
    }

    public MarkerOptions title(String str) {
        this.b = str;
        return this;
    }

    public MarkerOptions snippet(String str) {
        this.c = str;
        return this;
    }

    public MarkerOptions rotation(float f) {
        this.j = f;
        return this;
    }

    public MarkerOptions position(LatLng latLng) {
        this.f5004a = latLng;
        return this;
    }

    public boolean ismClusterable() {
        return this.o;
    }

    public boolean isVisible() {
        return this.h;
    }

    public boolean isNewAnchorSetting() {
        return this.r;
    }

    public boolean isFlat() {
        return this.i;
    }

    public boolean isDraggable() {
        return this.g;
    }

    public boolean isClickable() {
        return this.s;
    }

    public MarkerOptions infoWindowAnchor(float f, float f2) {
        this.k = f;
        this.l = f2;
        return this;
    }

    public MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        this.d = bitmapDescriptor;
        return this;
    }

    public float getZIndex() {
        return this.n;
    }

    public String getTitle() {
        return this.b;
    }

    public String getSnippet() {
        return this.c;
    }

    public float getRotation() {
        return this.j;
    }

    public final LatLng getPosition() {
        return this.f5004a;
    }

    public float getMarkerAnchorV() {
        return this.q;
    }

    public float getMarkerAnchorU() {
        return this.p;
    }

    public float getInfoWindowAnchorV() {
        return this.l;
    }

    public float getInfoWindowAnchorU() {
        return this.k;
    }

    public BitmapDescriptor getIcon() {
        return this.d;
    }

    @Deprecated
    public float getAnchorV() {
        return this.f;
    }

    @Deprecated
    public float getAnchorU() {
        return this.e;
    }

    public float getAlpha() {
        return this.m;
    }

    public MarkerOptions flat(boolean z) {
        this.i = z;
        return this;
    }

    public MarkerOptions draggable(boolean z) {
        this.g = z;
        return this;
    }

    public MarkerOptions clusterable(boolean z) {
        this.o = z;
        return this;
    }

    public MarkerOptions clickable(boolean z) {
        this.s = z;
        return this;
    }

    public MarkerOptions anchorMarker(float f, float f2) {
        this.r = true;
        this.p = f;
        this.q = f2;
        return this;
    }

    @Deprecated
    public MarkerOptions anchor(float f, float f2) {
        this.r = false;
        this.e = f;
        this.f = f2;
        return this;
    }

    public MarkerOptions alpha(float f) {
        this.m = f;
        return this;
    }

    protected MarkerOptions(Parcel parcel) {
        this.e = 0.5f;
        this.f = 1.0f;
        this.h = true;
        this.i = false;
        this.j = 0.0f;
        this.k = 0.5f;
        this.l = 0.0f;
        this.m = 1.0f;
        this.o = false;
        this.p = 0.5f;
        this.q = 1.0f;
        this.r = true;
        this.s = true;
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.f5004a = (LatLng) parcelReader.readParcelable(2, LatLng.CREATOR, null);
        this.b = parcelReader.createString(3, null);
        this.c = parcelReader.createString(4, null);
        this.e = parcelReader.readFloat(5, 0.0f);
        this.f = parcelReader.readFloat(6, 0.0f);
        this.g = parcelReader.readBoolean(7, true);
        this.h = parcelReader.readBoolean(8, true);
        this.i = parcelReader.readBoolean(9, true);
        this.j = parcelReader.readFloat(10, 0.0f);
        this.k = parcelReader.readFloat(11, 0.0f);
        this.l = parcelReader.readFloat(12, 0.0f);
        this.m = parcelReader.readFloat(13, 0.0f);
        this.n = parcelReader.readFloat(14, 0.0f);
        this.o = parcelReader.readBoolean(15, false);
        IBinder readIBinder = parcelReader.readIBinder(16, null);
        if (readIBinder != null) {
            this.d = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(readIBinder));
        }
        this.p = parcelReader.readFloat(17, 0.0f);
        this.q = parcelReader.readFloat(18, 0.0f);
        this.r = parcelReader.readBoolean(19, false);
        this.s = parcelReader.readBoolean(20, true);
    }

    public MarkerOptions() {
        this.e = 0.5f;
        this.f = 1.0f;
        this.h = true;
        this.i = false;
        this.j = 0.0f;
        this.k = 0.5f;
        this.l = 0.0f;
        this.m = 1.0f;
        this.o = false;
        this.p = 0.5f;
        this.q = 1.0f;
        this.r = true;
        this.s = true;
    }
}
