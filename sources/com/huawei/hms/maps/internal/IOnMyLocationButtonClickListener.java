package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes9.dex */
public interface IOnMyLocationButtonClickListener extends IInterface {
    boolean onMyLocationButtonClick();

    public static abstract class Stub extends Binder implements IOnMyLocationButtonClickListener {
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
                parcel2.writeString("com.huawei.hms.maps.internal.IOnMyLocationButtonClickListener");
                return true;
            }
            parcel.enforceInterface("com.huawei.hms.maps.internal.IOnMyLocationButtonClickListener");
            boolean onMyLocationButtonClick = onMyLocationButtonClick();
            parcel2.writeNoException();
            parcel2.writeInt(onMyLocationButtonClick ? 1 : 0);
            return true;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IOnMyLocationButtonClickListener");
        }
    }
}
