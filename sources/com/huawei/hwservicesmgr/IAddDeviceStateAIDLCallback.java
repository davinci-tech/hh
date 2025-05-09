package com.huawei.hwservicesmgr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IAddDeviceStateAIDLCallback extends IInterface {
    public static final String DESCRIPTOR = "com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback";

    void onAddDeviceState(int i) throws RemoteException;

    /* loaded from: classes5.dex */
    public static abstract class Stub extends Binder implements IAddDeviceStateAIDLCallback {
        static final int TRANSACTION_onAddDeviceState = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IAddDeviceStateAIDLCallback.DESCRIPTOR);
        }

        public static IAddDeviceStateAIDLCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IAddDeviceStateAIDLCallback.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAddDeviceStateAIDLCallback)) {
                return (IAddDeviceStateAIDLCallback) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IAddDeviceStateAIDLCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IAddDeviceStateAIDLCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                onAddDeviceState(parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        static class a implements IAddDeviceStateAIDLCallback {
            private IBinder c;

            a(IBinder iBinder) {
                this.c = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.c;
            }

            @Override // com.huawei.hwservicesmgr.IAddDeviceStateAIDLCallback
            public void onAddDeviceState(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IAddDeviceStateAIDLCallback.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.c.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
