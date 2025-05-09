package com.amap.api.maps.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.col.p0003sl.dv;
import com.autonavi.ae.gmap.maploader.Pools;

/* loaded from: classes2.dex */
public final class Tile implements Parcelable {
    public static final b CREATOR = new b();
    private static final Pools.SynchronizedPool<Tile> M_POOL = new Pools.SynchronizedPool<>(18);
    private BitmapDescriptor bitmapDescriptor;
    public final byte[] data;
    public final int height;
    private final boolean isBitmap;
    private final int mVersionCode;
    public final int width;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public static Tile obtain(int i, int i2, byte[] bArr) {
        Tile acquire = M_POOL.acquire();
        return acquire != null ? acquire : new Tile(i, i2, bArr);
    }

    public final void recycle() {
        M_POOL.release(this);
    }

    Tile(int i, int i2, int i3, byte[] bArr, boolean z) {
        this.mVersionCode = i;
        this.width = i2;
        this.height = i3;
        this.data = bArr;
        if (bArr != null) {
            try {
                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                this.bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(decodeByteArray);
                dv.a(decodeByteArray);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.isBitmap = z;
    }

    public Tile(int i, int i2, byte[] bArr) {
        this(1, i, i2, bArr, true);
    }

    public Tile(int i, int i2, byte[] bArr, boolean z) {
        this(1, i, i2, bArr, z);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mVersionCode);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeByteArray(this.data);
        parcel.writeInt(this.isBitmap ? 1 : 0);
    }
}
