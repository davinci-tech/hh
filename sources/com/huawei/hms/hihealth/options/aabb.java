package com.huawei.hms.hihealth.options;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface aabb extends IInterface {
    void onException(int i, String str) throws RemoteException;

    public static abstract class aab extends Binder implements aabb {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.huawei.hms.hihealth.options.OnRealTimeExceptionRemoteListener");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.huawei.hms.hihealth.options.OnRealTimeExceptionRemoteListener");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            onException(parcel.readInt(), parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        public aab() {
            attachInterface(this, "com.huawei.hms.hihealth.options.OnRealTimeExceptionRemoteListener");
        }
    }
}
