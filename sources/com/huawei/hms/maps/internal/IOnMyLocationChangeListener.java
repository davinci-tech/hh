package com.huawei.hms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.hms.feature.dynamic.IObjectWrapper;

/* loaded from: classes9.dex */
public interface IOnMyLocationChangeListener extends IInterface {
    void onMyLocationChange(IObjectWrapper iObjectWrapper);

    public static abstract class Stub extends Binder implements IOnMyLocationChangeListener {
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
                parcel2.writeString("com.huawei.hms.maps.internal.IOnMyLocationChangeListener");
                return true;
            }
            parcel.enforceInterface("com.huawei.hms.maps.internal.IOnMyLocationChangeListener");
            onMyLocationChange(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        public Stub() {
            attachInterface(this, "com.huawei.hms.maps.internal.IOnMyLocationChangeListener");
        }
    }
}
