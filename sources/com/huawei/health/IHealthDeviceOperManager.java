package com.huawei.health;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IHealthDeviceOperManager extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.health.IHealthDeviceOperManager";

    void holdDevice(String str) throws RemoteException;

    void releaseDevice(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IHealthDeviceOperManager {
        static final int TRANSACTION_holdDevice = 1;
        static final int TRANSACTION_releaseDevice = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IHealthDeviceOperManager.DESCRIPTOR);
        }

        public static IHealthDeviceOperManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IHealthDeviceOperManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHealthDeviceOperManager)) {
                return (IHealthDeviceOperManager) queryLocalInterface;
            }
            return new c(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IHealthDeviceOperManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IHealthDeviceOperManager.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                holdDevice(parcel.readString());
                parcel2.writeNoException();
            } else if (i == 2) {
                releaseDevice(parcel.readString());
                parcel2.writeNoException();
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        /* loaded from: classes3.dex */
        static class c implements IHealthDeviceOperManager {
            private IBinder c;

            c(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.health.IHealthDeviceOperManager
            public void holdDevice(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHealthDeviceOperManager.DESCRIPTOR);
                    obtain.writeString(str);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.health.IHealthDeviceOperManager
            public void releaseDevice(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IHealthDeviceOperManager.DESCRIPTOR);
                    obtain.writeString(str);
                    this.c.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
