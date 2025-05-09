package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.maps.model.PointOfInterest;

/* loaded from: classes9.dex */
public interface IOnPoiClickListener extends IInterface {
    void onPoiClick(PointOfInterest pointOfInterest);

    public static abstract class Stub extends Binder implements IOnPoiClickListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.huawei.hms.maps.internal.IOnPoiClickListener");
                return true;
            }
            parcel.enforceInterface("com.huawei.hms.maps.internal.IOnPoiClickListener");
            onPoiClick(parcel.readInt() != 0 ? PointOfInterest.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IOnPoiClickListener");
        }
    }
}
